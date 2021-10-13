#Let Exp

IN 'in'
LET 'let'

%
<exp>:LetExp     ::= LET <letDecls> IN <exp>
%

LetExp
%%%
    // <exp>:LetExp     ::= LET <letDecls> IN <exp>

    @Override
    public Val eval( Env env ) {
        Env newEnv = letDecls.makeEnv( env );
        return exp.eval( newEnv );
    }

%%%
