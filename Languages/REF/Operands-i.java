Operands:import
%%%
import java.util.stream.Collectors;
import env.*;
%%%

Operands
%%%

    /**
     * Fetch the values of each expression in this parameter (operands) list.
     */
    public List< Val > evalOperands( Env env ) {
        return expList.stream()
                            .map( exp -> exp.eval( env ) )
                            .collect( Collectors.toList() );
    }

    /**
     * Create a list of references to the values of each expression in
     * this parameter (operands) list.
     */
    public List< Ref > evalOperandsRef( Env env ) {
        return expList.stream()
                            .map( exp -> exp.evalRef( env ) )
                            .collect( Collectors.toList() );
    }

    @Override
    public String toString() {
        return expList.stream()
                        .map( Exp::toString )
                        .collect( Collectors.joining( "," ) );
    }
%%%

