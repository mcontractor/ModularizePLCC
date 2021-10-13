package env2;

import java.util.List;

public interface ILetrecDecls<T,E> {
    public abstract Env addBindings(Env env);

    public abstract void addVarList(List<T> list);
    public abstract void addProcList(List<E> list);
}