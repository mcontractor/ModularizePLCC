
LetrecDecls:import
%%%
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
import env.*;
%%%


LetrecDecls
%%%

    // <letDecls>       **= <VAR> ASSIGN <exp>

    public Env makeEnv( Env oldEnv ) {
        assert varList.size() == expList.size(): "AST is messed up";

        // Create an empty environment
        
        Env newEnv = oldEnv.extendEnv( new Bindings() ); // ! will go away

        // For each var-proc pair, make a new Binding
        // out of the corresponding token "lexeme" and expr evaluation.
        
        List< Binding > bindings = new LinkedList<>();
        Iterator< Token > varIter = varList.iterator();
        Iterator< Exp > expIter = expList.iterator();
        while ( varIter.hasNext() ) {
            String varName = varIter.next().str;
            Val procVal = expIter.next().eval( newEnv ); // !
            // ProcVal procVal = (ProcVal)expIter.next().eval( newEnv ); // !
            Binding binding = new Binding( varName, new ValRef( procVal ) );
            bindings.add( binding );
        }

        // Change the environment to add the Bindings.
        newEnv.replaceBindings( new Bindings( bindings ) ); // !
        return newEnv;
    }


%%%
