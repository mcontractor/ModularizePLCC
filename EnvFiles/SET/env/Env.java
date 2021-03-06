package env;

import java.util.Arrays;
import java.util.List;

public abstract class Env {

    /**
     * Replace the bindings in this environment.
     * @param bindings the new bindings to use
     * @rit.post the old bindings, if there were any, are gone.
     */
    public abstract void replaceBindings( Bindings bindings );

    /**
     * Look up the symbol in the environment.
     */
    public abstract Ref applyEnvRef( String sym );

    /**
     * Look up the symbol in the environment
     * and then dereference it to get the value.
     */
    public Val applyEnv( String sym ) {
        return applyEnvRef(sym).deRef();
    }

    /**
     * Extend the current environment by adding bindings.
     * @return a new Env linked to this one.
     */
    public Env extendEnv( Bindings bindings ) {
        return new EnvNode( bindings, this );
    }

    /**
     * Add a new binding to the front of this environment's existing ones.
     */
    public abstract void addFirst( Binding newBinding );

    /**
     * Display, in a string, the sequence of all bindings,
     * newest to oldest, in this and in enclosing environments.
     */
    @Override
    public String toString() {
        return "{" + this.envListing() + "}";
    }

    /**
     * Show the bindings in this Env. and in all surrounding Env's.
     */
    public abstract String envListing();

    /**
     * The "end of sequence" object for an environment chain
     */
    public static final Env ENV_NULL = new Env() {

        public void replaceBindings( Bindings bindings ) {
            throw new RuntimeException("no bindings to replace");
        }

        @Override
        public void addFirst( Binding newBinding ) {
            throw new RuntimeException( "Can't add Binding to EnvNull" );
        }

        @Override
        public Ref applyEnvRef( String sym ) {
            throw new RuntimeException( "no binding for " + sym );
        }

        @Override
        public String envListing() {
            return "";
        }
    };
}
