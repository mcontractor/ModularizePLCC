package env;

import java.util.*;

public abstract class Val {

    public static final Val nil = new NilVal();
    public static final Val zero = new IntVal(0);
    public static final ListVal listNull = new ListNull();

    public static Val [] toArray(List<Val> valList) {
        int n = valList.size();
        return valList.toArray(new Val[n]);
    }

    public Val apply(List<Val> valList) {
        throw new PLCCException(this + ": not a procedure");
    }

    public Env env() {
        throw new PLCCException("<" + this + ">: not an environment");
    }

    public boolean isTrue() {
        // every Val is true except for an IntVal of zero,
        // an empty list, or a NilVal.
        return true;
    }

    public IntVal intVal() {
        throw new PLCCException(this + ": not an Int");
    }

    public ListVal listVal() {
        throw new PLCCException(this + ": not a List");
    }

    public ClassVal classVal() {
        throw new PLCCException(this + ": not a Class");
    }

    public ObjectVal objectVal() {
        throw new PLCCException(this + ": not an Object");
    }


    public ListNode listNode() {
        throw new PLCCException(this + ": not a nonempty List");
    }

    public boolean isList() {
        return false;
    }

    public boolean isObject() {
        return false;
    }

    public boolean isClass() {
        return false;
    }

    public ObjectVal makeObject(Ref objRef) {
        throw new PLCCException("new " + this + ": not a class");
    }

    public String putc() {
        return this.toString();
    }
}
