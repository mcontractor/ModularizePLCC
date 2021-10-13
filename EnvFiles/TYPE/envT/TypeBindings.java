package envT;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TypeBindings {

    public List<TypeBinding> bindingList;

    // create an empty list of bindings
    public TypeBindings() {
	bindingList = new ArrayList<TypeBinding>();
    }

    public TypeBindings(List<TypeBinding> bindingList) {
	this.bindingList = bindingList;
    }

    // idList is a list of Tokens/Strings
    // typeList is a list of Types
    public TypeBindings(List<?> idList, List<Type> typeList) {
	if (idList.size() != typeList.size())
	    throw new RuntimeException("TypeBindings: List size mismatch");
	bindingList = new ArrayList<TypeBinding>();
	Iterator<?> idIterator = idList.iterator();
	Iterator<Type> typeIterator = typeList.iterator();
	while (idIterator.hasNext()) {
	    String id = idIterator.next().toString();
	    Type typ = typeIterator.next();
	    bindingList.add(new TypeBinding(id, typ));
        }
    }

    public void add(String s, Type t) {
	bindingList.add(new TypeBinding(s, t));
    }

    public void addFirst(TypeBinding b) {
	bindingList.add(0,b);
    }

    public void add(TypeBinding b) {
	bindingList.add(b);
    }

    public int size() {
	return bindingList.size();
    }

    public String toString() {
	String s = "";
	for (TypeBinding b : bindingList)
	    s += " " + b;
	return s;
    }
}