package env2;
import java.util.*;

public interface IMethods {
    public abstract List<? extends IToken> getVarList();
    public abstract List<? extends IProc> getProcList();
}