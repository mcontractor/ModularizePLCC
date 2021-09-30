Prim:import 
%%%
import env.*;
%%%
Prim
%%%
    // apply the primitive to the passed values
    public abstract Val apply(Val [] va);
%%%


AddPrim:import 
%%%
import env.*;
%%%
AddPrim
%%%

    public String toString() {
  return "+";
    }

    public Val apply(Val [] va) {
        if (va.length != 2)
            throw new PLCCException("two arguments expected");
        int i0 = va[0].intVal().val;
        int i1 = va[1].intVal().val;
        return new IntVal(i0 + i1);
    }
%%%


SubPrim:import 
%%%
import env.*;
%%%
SubPrim
%%%

    public String toString() {
        return "-";
    }

    public Val apply(Val [] va) {
        if (va.length != 2)
            throw new PLCCException("two arguments expected");
        int i0 = va[0].intVal().val;
        int i1 = va[1].intVal().val;
        return new IntVal(i0 - i1);
    }
%%%


MulPrim:import 
%%%
import env.*;
%%%
MulPrim
%%%

    public String toString() {
        return "*";
    }

    public Val apply(Val [] va) {
        if (va.length != 2)
            throw new PLCCException("two arguments expected");
        int i0 = va[0].intVal().val;
        int i1 = va[1].intVal().val;
        return new IntVal(i0 * i1);
    }

%%%


DivPrim:import 
%%%
import env.*;
%%%
DivPrim
%%%

    public String toString() {
        return "/";
    }

    public Val apply(Val [] va) {
        if (va.length != 2)
            throw new PLCCException("two arguments expected");
        int i0 = va[0].intVal().val;
        int i1 = va[1].intVal().val;
        if (i1 == 0)
            throw new PLCCException("attempt to divide by zero");
        return new IntVal(i0 / i1);
    }

%%%


Add1Prim:import 
%%%
import env.*;
%%%
Add1Prim
%%%

    public String toString() {
        return "add1";
    }

    public Val apply(Val [] va) {
        if (va.length != 1)
            throw new PLCCException("one argument expected");
        int i0 = va[0].intVal().val;
        return new IntVal(i0 + 1);
    }

%%%


Sub1Prim:import 
%%%
import env.*;
%%%
Sub1Prim
%%%

    public String toString() {
        return "sub1";
    }

    public Val apply(Val [] va) {
        if (va.length != 1)
            throw new PLCCException("one argument expected");
        int i0 = va[0].intVal().val;
        return new IntVal(i0 - 1);
    }

%%%


ZeropPrim:import 
%%%
import env.*;
%%%
ZeropPrim
%%%

    public String toString() {
        return "zero?";
    }

    public Val apply(Val [] va) {
        if (va.length != 1)
            throw new PLCCException("one argument expected");
        int i0 = va[0].intVal().val;
        return new IntVal(i0 == 0 ? 1 : 0);
    }

%%%


LTPrim:import 
%%%
import env.*;
%%%
LTPrim
%%%
    public String toString() {
        return "<?";
    }

    public Val apply(Val [] va) {
        if (va.length != 2)
            throw new PLCCException("two arguments expected");
        int i0 = va[0].intVal().val;
        int i1 = va[1].intVal().val;
        return new IntVal(i0 < i1 ? 1 : 0);
    }
%%%


LEPrim:import 
%%%
import env.*;
%%%
LEPrim
%%%
    public String toString() {
        return "<=?";
    }

    public Val apply(Val [] va) {
        if (va.length != 2)
            throw new PLCCException("two arguments expected");
        int i0 = va[0].intVal().val;
        int i1 = va[1].intVal().val;
        return new IntVal(i0 <= i1 ? 1 : 0);
    }
%%%


GTPrim:import 
%%%
import env.*;
%%%
GTPrim
%%%
    public String toString() {
        return ">?";
    }

    public Val apply(Val [] va) {
        if (va.length != 2)
            throw new PLCCException("two arguments expected");
        int i0 = va[0].intVal().val;
        int i1 = va[1].intVal().val;
        return new IntVal(i0 > i1 ? 1 : 0);
    }
%%%


GEPrim:import 
%%%
import env.*;
%%%
GEPrim
%%%
    public String toString() {
        return ">=?";
    }

    public Val apply(Val [] va) {
        if (va.length != 2)
            throw new PLCCException("two arguments expected");
        int i0 = va[0].intVal().val;
        int i1 = va[1].intVal().val;
        return new IntVal(i0 >= i1 ? 1 : 0);
    }
%%%


NEPrim:import 
%%%
import env.*;
%%%
NEPrim
%%%
    public String toString() {
        return "<>?";
    }

    public Val apply(Val [] va) {
        if (va.length != 2)
            throw new PLCCException("two arguments expected");
        int i0 = va[0].intVal().val;
        int i1 = va[1].intVal().val;
        return new IntVal(i0 != i1 ? 1 : 0);
    }
%%%


EQPrim:import 
%%%
import env.*;
%%%
EQPrim
%%%
    public String toString() {
        return "=?";
    }

    public Val apply(Val [] va) {
        if (va.length != 2)
            throw new PLCCException("two arguments expected");
        int i0 = va[0].intVal().val;
        int i1 = va[1].intVal().val;
        return new IntVal(i0 == i1 ? 1 : 0);
    }
%%%


ObjectpPrim:import 
%%%
import env.*;
%%%
ObjectpPrim
%%%
    public Val apply(Val [] va) {
	if (va.length != 1)
            throw new PLCCException("one argument expected");
        Val v0 = va[0];
        return new IntVal(v0.isObject() ? 1 : 0);
   }
%%%


ClasspPrim:import 
%%%
import env.*;
%%%
ClasspPrim
%%%
    public Val apply(Val [] va) {
	if (va.length != 1)
            throw new PLCCException("one argument expected");
        Val v0 = va[0];
        return new IntVal(v0.isClass() ? 1 : 0);
   }
%%%

ListpPrim:import 
%%%
import env.*;
%%%
ListpPrim
%%%
    public Val apply(Val [] va) {
	if (va.length != 1)
	    throw new PLCCException("one argument expected");
        Val v0 = va[0];
	return new IntVal(v0.isList() ? 1 : 0);
    }
%%%


FirstPrim:import 
%%%
import env.*;
%%%
FirstPrim
%%%
    public Val apply(Val [] va) {
        if (va.length != 1)
            throw new PLCCException("one argument expected");
        ListNode lst = va[0].listNode();
        return lst.val;
    }
%%%

RestPrim:import 
%%%
import env.*;
%%%
RestPrim
%%%
    public Val apply(Val [] va) {
        if (va.length != 1)
            throw new PLCCException("one argument expected");
        ListNode lst = va[0].listNode();
        return lst.listVal;
    }
%%%


AddFirstPrim:import 
%%%
import env.*;
%%%
AddFirstPrim
%%%
    public Val apply(Val [] va) {
        if (va.length != 2)
            throw new PLCCException("two arguments expected");
        Val v = va[0];
        ListVal lst = va[1].listVal();
        return new ListNode(v, lst);
    }
%%%


LenPrim:import 
%%%
import env.*;
%%%
LenPrim
%%%
    public Val apply(Val [] va) {
        if (va.length != 1)
            throw new PLCCException("one argument expected");
        ListVal lst = va[0].listVal();
        return new IntVal(lst.len());
    }

%%%
