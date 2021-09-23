Program:import 
%%%
import env.*;
%%%

Program
%%%

    //// **** Note that this class is abstract. ****

    public static Env initEnv = Env.ENV_NULL.extendEnv(
            new Bindings( new LinkedList<>() )
    );

%%%

