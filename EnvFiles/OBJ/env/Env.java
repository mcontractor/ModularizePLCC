package env;

import java.util.*;

public abstract class Env {
    public static Env progEnv;
    public static final Env empty = new EnvNull();

    public abstract Env add(Binding b);

    public static void checkDuplicates(List<IToken> varList, String msg) {
        // throws an exeption if the varList has duplicate vars
        Set<String> varSet = new HashSet<String>(2*varList.size());
        for (IToken var: varList) {
            String str = var.toString();
            if (varSet.contains(str)) {
                msg = "duplicate ID " + str + msg;
                throw new PLCCException("Semantic error",  msg);
            }
            varSet.add(str);
        }
    }

    public static void checkDuplicates(List<IToken> varList) {
        checkDuplicates(varList, "");
    }

    public static Env initEnv() {
        // Create bindings for some symbols
        return new EnvNode(new Bindings(), empty);
    }

    public static Env initAndSaveEnv() {
        // Create bindings for some symbols
        progEnv = new EnvNode(new Bindings(), empty);
        return progEnv;
    }

    public abstract Binding lookup(String sym, boolean local);

    public Binding lookup(String sym) {
        return lookup(sym, true); // defaults to local
    }

    public Ref applyEnvRef(String sym) {
        Binding b = lookup(sym, false);
        if (b != null)
            return b.ref;
        throw new PLCCException("no binding for "+sym);
    }

    public Ref applyEnvRef(IToken tok) {
        return applyEnvRef(tok.toString());
    }

    public Val applyEnv(String sym) {
        return applyEnvRef(sym).deRef();
    }

    public Val applyEnv(IToken tok) {
        return applyEnvRef(tok.toString()).deRef();
    }

    public Env extendEnvRef(Bindings bindings) {
        return new EnvNode(bindings, this);
    }

    public abstract Bindings getBindings();

    public abstract String toString(int n);

    public static Env getEnv() {
        return progEnv;
    }
}
