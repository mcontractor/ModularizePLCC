package envT;

public class TypeBinding {

    public String id;
    public Type type;

    public TypeBinding(String id, Type type) {
	this.id = id;
	this.type = type;
    }

    public String toString() {
	return "[" + id + "->" + type.toString() + "]";
    }

}