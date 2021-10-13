package env;

public class BoolVal extends Val {

    public boolean val;
    
    public BoolVal(boolean b) {
	val = b;
    }
    
    public boolean isTrue() {
	return val;
    }

    public BoolVal boolVal() {
        return this;
    }
    
    public String toString() {
	return "" + val;
    }

    public static void main(String [] args) {
	Val t = new BoolVal(true);
	Val f = new BoolVal(false);
	System.out.println("t=" + t);
	System.out.println("f=" + f);
    }
    
}