Program
%%%
    public static Env initEnv = Env.ENV_NULL;
%%%

Eval
%%%

    @Override
    public String toString() {
        return exp.eval( Program.initEnv ).toString();
    }

    public String toStringOld() {
	return exp.toString();
    }
%%%

