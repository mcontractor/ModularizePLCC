package env;

import envT.*;
import java.util.List;

public class ProcVal extends Val {

    public IFormals formals;
    public TypeExp returnTypeExp;
    public IExp body;
    public Env env;

    public ProcVal(IFormals formals, TypeExp returnTypeExp, IExp body, Env env) {
	    this.formals = formals;
        this.returnTypeExp = returnTypeExp;
        this.body = body;
        this.env = env;
    }

    public ProcVal procVal() {
        return this;
    }

    public Val apply(List<Ref> refList, Env e) {
        Bindings bindings = new Bindings(formals.getVarList(), refList);
        Env nenv = env.extendEnvRef(bindings);
        return body.eval(nenv);
    } 

    public String toString() {
        return "proc(" + formals + "):" + returnTypeExp.toType();
    }
}