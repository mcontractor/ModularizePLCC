package env;

public class NilVal extends Val {

    public boolean isTrue() {
        return false;
    }

    public String toString() {
        return "nil";
    }

}
