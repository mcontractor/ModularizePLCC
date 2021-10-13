# if-else-then file

IF 'if'
THEN 'then'
ELSE 'else'

%
<exp>:IfExp      ::= IF <exp>test THEN <exp>thenPart ELSE <exp>elsePart
%

IfExp
%%%
    public String toString() {
        return test + " ? " + thenPart + " : " + elsePart;
    }
    public Val eval( Env env ) {
        return test.eval( env ).isTrue() ? thenPart.eval( env ) : elsePart.eval( env );
    }
%%%
