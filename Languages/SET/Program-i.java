Program:import 
%%%
import env.*;
%%%

Program
%%%
    public static Env initEnv = Env.ENV_NULL.extendEnv(
            new Bindings( new LinkedList<>() )
    );

%%%

