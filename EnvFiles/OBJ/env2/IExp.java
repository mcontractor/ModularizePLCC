package env2;

public interface IExp {
    /**
     * Values are assumed to represent true
     * except in special cases
     */
    public abstract Val eval( Env env1 );
}
