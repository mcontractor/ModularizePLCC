package env;

public interface IExp {
    /**
     * Values are assumed to represent true
     * except in special cases
     */
    public abstract Val eval( Env env1 );
}
