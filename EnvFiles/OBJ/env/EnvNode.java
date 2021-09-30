package env;

import java.util.*;

public class EnvNode extends Env {

    public Bindings bindings; // list of local bindings
    public Env env;           // next set of bindings

    // create an environment
    public EnvNode(Bindings bindings, Env env) {
        this.bindings = bindings;
        this.env = env;
    }

    public Bindings getBindings() {
        return bindings;
    }

    public Binding lookup(String sym, boolean local) {
        Binding b = bindings.lookup(sym);
        if (b != null)
            return b;
        if (local)
            return null;
        return env.lookup(sym, false);
    }

    public Env add(Binding b) {
        bindings.add(b);
        return this;
    }

    public String toString(int n) {
        return n + ":" + bindings.toString() + "\n" + env.toString(n+1);
    }

    public String toString() {
        return bindings.toString() + "----\n" + env;
    }

}