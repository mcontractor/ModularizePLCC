package envT;

public class TypeEnvNode extends TypeEnv {

    public TypeBindings bindings; // list of local bindings
    public TypeEnv typeEnv;           // next set of bindings
    
    // create an environment 
    public TypeEnvNode(TypeBindings bindings, TypeEnv typeEnv) {
	this.bindings = bindings;
	this.typeEnv = typeEnv;
    }
    
    // find the Type corresponding to a given id
    public Type applyTypeEnv(String sym) {
        // first look in the local bindings
	for (TypeBinding b : bindings.bindingList) {
	    if (sym.equals(b.id))
                return b.type;
        }
	// if nothing left, we don't have a match in this list of bindings
	return typeEnv.applyTypeEnv(sym);
    }

    public String toString(int n) {
	return n + ":" + bindings.toString() + "\n" + typeEnv.toString(n+1);
    }

    public String toString() {
	return bindings.toString() + "\n" + typeEnv;
    }

    public void addFirst(TypeBinding b) {
        bindings.addFirst(b);
    }

    public void add(TypeBinding b) {
	bindings.add(b);
    }

}