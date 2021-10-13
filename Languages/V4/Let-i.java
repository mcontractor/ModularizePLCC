# letExp and letDecls

IN 'in'
LET 'let'

%
<exp>:LetExp     ::= LET <letDecls> IN <exp>
<letDecls>       **= <VAR> ASSIGN <exp>
%

LetDecls:import
%%%
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
%%%


LetExp
%%%
    // <exp>:LetExp     ::= LET <letDecls> IN <exp>

    public Val eval( Env env ) {
        Env newEnv = letDecls.makeEnv( env );
        return exp.eval( newEnv );
    }

%%%


LetDecls
%%%

    // <letDecls>       **= <VAR> ASSIGN <exp>

    public Env makeEnv( Env oldEnv ) {
        assert varList.size() == expList.size(): "AST is messed up";

        // For each var-exp pair, make a new Binding
        // out of the corresponding token "lexeme" and expr evaluation.
        
        List< Binding > bindings = new LinkedList<>();
        Iterator< Token > varIter = varList.iterator();
        Iterator< Exp > expIter = expList.iterator();
        while ( varIter.hasNext() ) {
            Binding binding = new Binding(
                varIter.next().str,
                expIter.next().eval( oldEnv )
                );
            bindings.add( binding );
        }

        // Make an environment out of the Bindings.
        return oldEnv.extendEnv( new Bindings( bindings ) );
    }


%%%
