package envT;

public class IntType extends Type {

    public void checkEquals(Type t) {
	t.checkIntType(this);
    }

    public void checkIntType(IntType t) {
        // this IntType equals any other IntType
    }

    public String toString() {
        return "int";
    }

}