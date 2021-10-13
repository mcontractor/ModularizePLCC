package envT;
import java.util.*;

public abstract class TypeEnv {

    public static TypeEnv initTypeEnv() {
	// an environment with an empty local environment
        return new TypeEnvNode(new TypeBindings(), new TypeEnvNull());
    }

    public Type applyTypeEnv(String sym) {
	throw new RuntimeException("no type binding for " + sym);
    }

    public void addFirst(TypeBinding b) {
	throw new RuntimeException("no type bindings to add to");
    }

    public void add(TypeBinding b) {
	throw new RuntimeException("no type bindings to add to");
    }

    public TypeEnv extendTypeEnv(TypeBindings bindings) {
	return new TypeEnvNode(bindings, this);
    }

    // check for duplicate variable names
    public static void checkDuplicates(List<String> varList) {
        Map<String,String> map = new HashMap<String,String>();
        for (String id : varList) {
            String s = map.get(id);
            if (s != null)
                throw new RuntimeException("duplicate identifier: " + id);
            map.put(id, id);
        }
    }

    public abstract String toString(int n);

}
