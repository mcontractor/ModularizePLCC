# seqExps

LBRACE '\{'
RBRACE '\}'
SEMI ';'

%
<exp>:SeqExp     ::= LBRACE <exp> <seqExps> RBRACE
<seqExps>        **= SEMI <exp>
%

SeqExp
%%%
public Val eval( Env env ) {
    Val v = exp.eval( env );
    for ( Exp e : seqExps.expList ) {
        v = e.eval( env );
    }
return v;
    }
%%%
