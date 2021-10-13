# ProcExp and ProcAppExp

PROC 'proc'
DOT '\.'

%
<exp>:ProcExp    ::= PROC LPAREN <formals> RPAREN <exp> # param list, body
<exp>:AppExp     ::= DOT <exp> LPAREN <operands> RPAREN # APPly (call) a proc
<formals>        **= <VAR> +COMMA
%


ProcExp
%%%
    /**
     * Create the value of a procedure.
     * Remember, this is NOT calling the procedure!
     */
    public Val eval( Env env ) {
        return new ProcVal( formals, exp, env );
        // return proc.makeClosure( env );
    }

    @Override
    public String toString() {
        return "λ" + formals + " { return " + exp + " }";
        // return proc.toString();
    }
%%%


Formals
%%%
    // The real work is handled directly in the Proc class.

    public String toString() {
        String formals = "(";
        String sep = "";
        for ( Token v : varList ) {
            formals += sep + v;
            sep = ",";
        }
        return formals + ")";
    }
%%%


AppExp:import
%%%
import java.util.List;
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
            List< Val > args = operands.evalOperands( env );
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

