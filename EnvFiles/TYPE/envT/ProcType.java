package envT;

import java.util.List;

// the declared type of a procedure
public class ProcType extends Type {

    public List<Type> paramTypeList;
    public Type returnType;

    public ProcType(List<Type> paramTypeList, Type returnType) {
	this.paramTypeList = paramTypeList;
	this.returnType = returnType;
    }

    public ProcType procType() {
        return this;
    }

    public void checkEquals(Type t) {
        t.checkProcType(this);
    }

    // be silent if this is a ProcType
    public void checkProcType() {
    }

    // check to see if the type of the ProcType object t is the same
    // as this ProcType object
    public void checkProcType(ProcType t) {
	// first check the return types
	this.returnType.checkEquals(t.returnType);
        // then check the types of the formal parameters
        checkEqualTypes(this.paramTypeList, t.paramTypeList);
    }
	
    public String toString() {
	String s = "[";
        String sep = "";
        for (Type t : paramTypeList) {
	    s += sep + t;
            sep = ",";
        }
        s += "=>" + returnType + "]";
        return s;
    }
       

}