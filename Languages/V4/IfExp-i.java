#if then elsePart

ELSE 'else'
IF 'if'
THEN 'then'

%
<exp>:IfExp      ::= IF <exp>test THEN <exp>thenPart ELSE <exp>elsePart
%
IfExp
%%%
    @Override
    public String toString() {
        return test + " ? " + thenPart + " : " + elsePart;
    }
    @Override
    public Val eval( Env env ) {
        return test.eval( env ).isTrue() ? thenPart.eval( env ) : elsePart.eval( env );
    }
%%%

