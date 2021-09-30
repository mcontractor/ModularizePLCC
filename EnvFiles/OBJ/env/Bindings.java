package env;

import java.util.*;

public class Bindings {

    public List<Binding> bindingList;

    // create an empty list of bindings
    public Bindings() {
        bindingList = new ArrayList<Binding>();
    }

    public Bindings(List<Binding> bindingList) {
        this.bindingList = bindingList;
    }

    public Binding lookup(String sym) {
        for (Binding b: bindingList)
            if (sym.equals(b.id))
                return b;
        return null;
    }

    // idList is a list of Tokens/Strings (whatever has a toString())
    // valList is a list of Vals
    public Bindings(List<?> idList, List<Ref> refList) {
        bindingList = new ArrayList<Binding>();
        Iterator<?> idIter = idList.iterator();
        Iterator<Ref> refIter = refList.iterator();
        while (idIter.hasNext()) {
            String s = idIter.next().toString();
            Ref r = refIter.next();
            this.add(new Binding(s, r));
        }
    }

    public void add(Binding b) {
        bindingList.add(b);
    }

    public void add(String s, Ref r) {
        add(new Binding(s, r));
    }

    public int size() {
        return bindingList.size();
    }

    public String toString() {
        String s = "";
        for (Binding b : bindingList)
            s += b + "\n";
        return s;
    }
}