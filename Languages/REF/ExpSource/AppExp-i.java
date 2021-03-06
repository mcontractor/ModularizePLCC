AppExp:import
%%%
import java.util.List;
import env.*;
%%%

AppExp
%%%
    /**
     * Compute the value of a procedure call.
     */
    public Val eval( Env env ) {
        Val v = null;
        try {
            v = exp.eval( env ); // Fetch the proc itself.
            ProcVal pv = (ProcVal)v;
            List< Ref > args = operands.evalOperandsRef( env );
            // Have the ProcVal execute its body.
            return pv.apply( args, env );
        }
        catch( ClassCastException cce ) {
            throw new RuntimeException( v.getClass() + " is not a proc." );
        }
    }

    @Override
    public String toString() {
        return "CALL [" + exp + "](" + operands + ")";
    }
%%%

