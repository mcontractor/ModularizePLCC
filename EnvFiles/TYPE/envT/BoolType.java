package envT;

public class BoolType extends Type {

    public void checkEquals(Type t) {
	t.checkBoolType(this);
    }

    public void checkBoolType(BoolType t) {
	// this BoolType equals any other BoolType!
    }

    public String toString() {
        return "bool";
    }
}