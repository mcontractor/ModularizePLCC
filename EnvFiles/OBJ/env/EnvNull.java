package env;

import java.util.*;

public class EnvNull extends Env {

    // create an environment
    public EnvNull() {
    }

    public Binding lookup(String sym, boolean local) {
        return null;
    }

    public Bindings getBindings() {
        throw new PLCCException("no bindings");
    }

    public Env add(Binding b) {
        throw new PLCCException("no bindings to add to");
    }

    public String toString(int n) {
        return n + ":\n";
    }

    public String toString() {
        return "\n";
    }
}