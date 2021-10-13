package env2;

import java.util.List;

public interface IFormals {
    public abstract String toString();
    public abstract List<? extends IToken> getVarList();
}
