package env;
import java.util.*;

public abstract class Val {

    public static Val [] toArray(List<Val> valList) {
        int n = valList.size();
        return valList.toArray(new Val[n]);
    }

    public IntVal intVal() {
        throw new RuntimeException(this + ": not an Int");
    }

    public ProcVal procVal() {
        throw new RuntimeException(this + ": not a Proc");
    }
          
    public BoolVal boolVal() {
        throw new RuntimeException(this + ": not a Bool");
    }

    public boolean isTrue() {
        return false;
    }

    public Val apply(List<Ref> refList, Env e) {
	throw new RuntimeException("cannot apply " + this);
    }
}

