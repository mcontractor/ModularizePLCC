Methods:import
%%%
import env.*;
%%%

Methods:class
%%%
implements IMethods
%%%
Methods
%%%
    public Env addMethodBindings(Env env) {
        if (varList.size() == 0)
        return env;
        List<Exp> expList = new ArrayList<Exp>();
        for (Proc p: procList)
        expList.add(new ProcExp(p));
        return new LetDecls(varList, expList).addLetrecBindings(env);
    }
%%%

Fields:import
%%%
import env.*;
%%%

Fields:class
%%%
implements IFields
%%%
Fields
%%%
    public void addFieldBindings(Bindings fieldBindings) {
        for (Token t: varList) {
        String s = t.toString();
        fieldBindings.add(s, new ValRef(Val.nil));
        }
    }
%%%