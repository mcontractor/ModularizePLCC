# class methods

Program:import
%%%
import env.*;
%%%

Program:class
%%%
implements IProgram
%%%

Program
%%%

    public static Env initEnv = Env.initAndSaveEnv();

%%%


Define:import
%%%
import env.*;
%%%
Define
%%%
    // notice that calling toString triggers a modification
    // of the initial environment
    public String toString() {
        String id = var.toString();
        Env env = Env.getEnv();
        Val val = exp.eval(env);
        Ref ref = new ValRef(val);
        Binding b = env.lookup(id);
        if (b != null)
            b.ref = ref;
        else
            env.add(new Binding(id, ref));
        return id;
    }
%%%


Eval:import 
%%%
import env.*;
%%%
Eval
%%%
    public String toString() {
        return exp.eval(Env.getEnv()).toString();
    }
%%%


Exp:import 
%%%
import env.*;
%%%
Exp:class 
%%%
implements IExp
%%%
Exp
%%%
    public abstract Val eval(Env env);
%%%


ExitExp:import 
%%%
import env.*;
%%%
ExitExp
%%%
    public Val eval(Env env) {
        throw new PLCCException("...exiting...");
    }
%%%


PrimappExp:import 
%%%
import env.*;
%%%
PrimappExp
%%%

    public String toString() {
        return prim.toString()+ "(" + rands + ")";
    }

    public Val eval(Env env) {
        // evaluate the terms in the expression list
        // and apply the prim to the array of integer results
        List<Val> valList = rands.evalRands(env);
        Val [] va = Val.toArray(valList);
        return prim.apply(va);
    }

%%%

LitExp:import 
%%%
import env.*;
%%%
LitExp
%%%

    public Val eval(Env env) {
        return new IntVal(lit.toString());
    }

    public String toString() {
        return lit.toString();
    }

%%%


ChrExp:import 
%%%
import env.*;
%%%
ChrExp
%%%
    public Val eval(Env env) {
        return new IntVal(chr.toString().charAt(1));
    }

    public String toString() {
        return chr.toString();
    }
%%%


StrngExp:import 
%%%
import env.*;
%%%
StrngExp
%%%
    public Val eval(Env env) {
        String s = strng.toString();
        int len = s.length()-1;
        LinkedList<Character> chars = new LinkedList<Character>();
        // We know that s.charAt(0) is '"'
        // and that s.charAt(len) is '"'
        int pos = 1;
        while (pos < len) {
            char c = s.charAt(pos++);
            if (c == '\\') {
                // escape!
                c = s.charAt(pos++);
                switch(c) {
                case 'a' : c = 7; break;
                case 'b' : c = 8;  break;
                case 't' : c = 9;  break;
                case 'n' : c = 10; break;
                case 'f' : c = 12; break;
                case 'r' : c = 13; break;
                default  : break;
                }
            }
            chars.add(0, c); // add to beginning of list
        }
        // chars is in reverse order -- now build a ListVal in proper order
        ListVal lst = Val.listNull;
        for (Character c : chars) {
            lst = lst.add(new IntVal(c));
        }
        return lst;
    }

    public String toString() {
        return strng.toString();
    }
%%%


IfExp:import 
%%%
import env.*;
%%%
IfExp
%%%
    public Val eval(Env env) {
        Val testVal = testExp.eval(env);
        if (testVal.isTrue())
            return trueExp.eval(env);
        else
            return falseExp.eval(env);
    }

    public String toString() {
        return "if " + testExp + " then " + trueExp + " else " +falseExp;
    }
%%%


VarExp:import 
%%%
import env.*;
%%%
VarExp
%%%

    public Val eval(Env env) {
        return env.applyEnv(var);
    }

    public String toString() {
        return var.toString();
    }

%%%


LetExp:import 
%%%
import env.*;
%%%
LetExp
%%%
    public Val eval(Env env) {
        Env nenv = letDecls.addBindings(env);
        return exp.eval(nenv);
    }

    public String toString() {
        return " ...LetExp... ";
    }
%%%


LetrecExp:import 
%%%
import env.*;
%%%
LetrecExp
%%%
    public Val eval(Env env) {
        Env nenv = letDecls.addLetrecBindings(env);
        return exp.eval(nenv);
    }

    public String toString() {
        return " ...LetrecExp... ";
    }
%%%


ProcExp:import 
%%%
import env.*;
%%%
ProcExp
%%%
    public Val eval(Env env) {
        return proc.makeClosure(env);
    }

    public String toString() {
        return " ...ProcExp... ";
    }
%%%


ListExp:import 
%%%
import env.*;
%%%
ListExp
%%%
    public Val eval(Env env) {
        List<Val> valList = rands.evalRands(env);
        ListVal listVal = Val.listNull;
        int n = valList.size();
        // process the elements in reverse order
        while (n > 0)
            listVal = new ListNode(valList.get(--n), listVal);
        return listVal;
    }

    public String toString() {
        return " ...ListExp... ";
    }
%%%


ClassExp:import 
%%%
import env.*;
%%%
ClassExp
%%%
    public Val eval(Env env) {
        return classDecl.eval(env);
    }

%%%

ClassDecl:import 
%%%
import env.*;
%%%
ClassDecl
%%%
    public Val eval(Env env) {
        ClassVal superClass = ext.toClassVal(env);
        return new StdClass(env, superClass, statics, fields, methods);
    }
%%%

Statics:import
%%%
import env.*;
%%%
Statics:class
%%%
implements IStatics
%%%
Statics
%%%
    // Add new bindings to the static bindings -- behaves like top-level
    // defines.
    public void addStaticBindings(Env env) {
        Iterator<Token> varIter = varList.iterator(); // LHS identifiers
        Iterator<Exp> expIter = expList.iterator();    // RHS expressions
        while(varIter.hasNext()) {
            Token var = varIter.next();
            String id = var.toString(); // the LHS identifier
            Exp exp = expIter.next();    // the RHS expression
            Val val = exp.eval(env);
            Ref ref = new ValRef(val);
            Binding b = env.lookup(id);
            if (b != null)
                b.ref = ref;
            else
                env.add(new Binding(id, ref));
        }
    }
%%%

NewExp:import 
%%%
import env.*;
%%%
NewExp
%%%
    public Val eval(Env env) {
        // get the class from which this object will be created
        Val val = exp.eval(env);
        // create a reference to a dummy value (nil)
        Ref objRef = new ValRef(Val.nil);
        // let the class create the object
        ObjectVal objectVal = val.makeObject(objRef);
        // set the reference to the newly created object
        return objRef.setRef(objectVal);
    }
%%%


AppExp:import 
%%%
import env.*;
%%%
AppExp
%%%

    public Val eval(Env env) {
        Val p = exp.eval(env);
        List<Val> valList = rands.evalRands(env);
        return p.apply(valList);
    }

    public String toString() {
        return " ...AppExp... ";
    }

%%%

SeqExp:import 
%%%
import env.*;
%%%
SeqExp
%%%
    public Val eval(Env env) {
        Val v = exp.eval(env);
        for (Exp e : seqExps.expList)
            v = e.eval(env);
        return v;
    }

    public String toString() {
        return " ...SeqExp... ";
    }

%%%

SetExp:import 
%%%
import env.*;
%%%
SetExp
%%%
    public Val eval(Env env) {
        // return loc.getEnv(env).applyEnvRef(var).setRef(exp.eval(env));
        Env nenv = loc.getEnv(env);
        Ref ref = nenv.applyEnvRef(var);
        Val v = exp.eval(env);
        return ref.setRef(v);
    }
%%%

Loc:import 
%%%
import env.*;
%%%
Loc
%%%
    public abstract Env getEnv(Env env);
%%%

ObjLoc:import 
%%%
import env.*;
%%%
ObjLoc
%%%
    public Env getEnv(Env env) {
        Val v = exp.eval(env);
        return v.env();
    }
%%%

SimpleLoc:import 
%%%
import env.*;
%%%
SimpleLoc
%%%
    public Env getEnv(Env env) {
        return env;
    }
%%%

EnvExp:import 
%%%
import env.*;
%%%
EnvExp
%%%
    public Val eval(Env env) {
        Val v = vExp.eval(env);
        return eExp.eval(v.env());
    }

%%%

EenvExp:import 
%%%
import env.*;
%%%
EenvExp
%%%
    public Val eval(Env env) {
        Val v = exp.eval(env);      // the environment object
        return mangle.eval(v, env);
    }
%%%


BangAtExp:import 
%%%
import env.*;
%%%
BangAtExp
%%%
    public Val eval(Env env) {
        return env.applyEnv("!@");
    }
%%%

Mangle:import 
%%%
import env.*;
%%%
Mangle
%%%
    public Val eval(Val v, Env env) {
        Iterator<Exp> expIter = expList.iterator();
        Iterator<Rands> randsIter = randsList.iterator();
        while (expIter.hasNext()) {
            // expIter.next() ProcExp to apply
            // randsIter.next() are the method rands -- evaluated in env
            // v.env() is the environment in which to build the ProcVal
            Val p = expIter.next().eval(v.env());
            List<Val> valList = randsIter.next().evalRands(env);
            v = p.apply(valList);
        }
        return v;
    }
%%%

WithExp:import 
%%%
import env.*;
%%%
WithExp
%%%
    public Val eval(Env env) {
        return new EnvExp(vExp, eExp).eval(env);
    }
%%%

NilExp:import 
%%%
import env.*;
%%%
NilExp
%%%
    public Val eval(Env env) {
        return Val.nil;
    }

%%%

SendExp:import 
%%%
import env.*;
%%%
SendExp
%%%

    public Val eval(Env env) {
        Val v = objExp.eval(env);
        Val p = procExp.eval(v.env()); // in the object env
        List<Val> valList = rands.evalRands(env); // in calling env
        return p.apply(valList);
    }

    public String toString() {
        return " ...SendExp... ";
    }

%%%

AtExp:import 
%%%
import env.*;
%%%
AtExp
%%%
    public Val eval(Env env) {
        return new ObjectVal(env);
    }

%%%

AtAtExp:import 
%%%
import env.*;
%%%
AtAtExp
%%%
    public Val eval(Env env) {
        System.out.println(env.toString(0));
        return new ObjectVal(env);
    }
%%%

DisplayExp:import 
%%%
import env.*;
%%%
DisplayExp
%%%
    public Val eval(Env env) {
        Val v = exp.eval(env);
        System.out.print(v);
        return v;
    }

%%%

Display1Exp:import 
%%%
import env.*;
%%%
Display1Exp
%%%
    public Val eval(Env env) {
        Val v = exp.eval(env);
        System.out.print(v+" ");
        return v;
    }

%%%

NewlineExp:import 
%%%
import env.*;
%%%
NewlineExp
%%%
    public Val eval(Env env) {
        System.out.println();
        return Val.nil;
    }

%%%

PutcExp:import 
%%%
import env.*;
%%%
PutcExp
%%%
    public Val eval(Env env) {
        Val v = exp.eval(env);
        System.out.print(v.putc());
        return Val.nil;
    }
%%%

ErrorExp:import 
%%%
import env.*;
%%%
ErrorExp
%%%
    public Val eval(Env env) {
        Val val = exp.eval(env);
        throw new PLCCException("error: " + val);
    }
%%%

PerrorExp:import 
%%%
import env.*;
%%%
PerrorExp
%%%
    public Val eval(Env env) {
        String str = strng.toString();
        int len = str.length();
        str = str.substring(1,len-1);
        throw new PLCCException("error: " + str);
    }
%%%

Ext:import 
%%%
import env.*;
%%%
Ext
%%%
    public abstract ClassVal toClassVal(Env env);
%%%

Ext0:import 
%%%
import env.*;
%%%
Ext0
%%%

    public ClassVal toClassVal(Env env) {
        return EnvClass.envClass; // a singleton
    }

%%%

Ext1:import 
%%%
import env.*;
%%%
Ext1
%%%
    public ClassVal toClassVal(Env env) {
        Val v = exp.eval(env);
        return v.classVal();
    }
%%%

Proc:import 
%%%
import env.*;
%%%
Proc
%%%
    public Val makeClosure(Env env) {
        return new ProcVal(formals, exp, env);
    }

%%%

LetDecls:import 
%%%
import env.*;
%%%
LetDecls
%%%
    public Env addBindings(Env env) {
        Rands rands = new Rands(expList);
        List<Val> valList = rands.evalRands(env);
        List<Ref> refList = Ref.valsToRefs(valList);
        Bindings bindings = new Bindings(varList, refList);
        return env.extendEnvRef(bindings);
    }

    public Env addLetrecBindings(Env env) {
        Env nenv = env.extendEnvRef(new Bindings());
        Iterator<Token> varIter = varList.iterator();
        Iterator<Exp> expIter = expList.iterator();
        while (varIter.hasNext()) {
            String str = varIter.next().toString();
            Val val = expIter.next().eval(nenv);
            Ref ref = new ValRef(val);
            nenv.add(new Binding(str, ref));
        }
        return nenv;
    }

    public String toString() {
        return " ...LetDecls... ";
    }
%%%

Rands:import 
%%%
import env.*;
%%%
Rands
%%%
    public String toString() {
        String s = "";   // the string to return
        String sep = ""; // no separator for the first expression
        // get all of the expressions in the expression list
        for (Exp e : expList) {
            s += sep + e;
            sep = ",";   // commas separate the rest of the expressions
        }
        return s;
    }

    public List<Val> evalRands(Env env) {
        List<Val> valList = new ArrayList<Val>();
        for (Exp e : expList)
            valList.add(e.eval(env));
        return valList;
    }

%%%


Formals:import 
%%%
import env.*;
%%%

Formals:class
%%%
implements IFormals
%%%

Formals
%%%
    // The real work is handled directly in the Proc class.

    public List<Token> getVarList() {
        return varList;
    }
%%%
