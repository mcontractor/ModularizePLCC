package env;

import java.util.*;

public class StdClass extends ClassVal {

    public ClassVal superClass;  // an EnvClass (top-level) or a StdClass
    public IFields fields;
    public IMethods methods;
    public Env staticEnv; // the static environment of this class

    public StdClass(
            Env localEnv,        // the environment where this class is created
            ClassVal superClass, // evaluated by ClassExp
            IStatics statics,     // static variable definitions
            IFields fields,       // field declarations
            IMethods methods) {   // method definitions
        this.superClass = superClass;
        this.fields = fields;
        this.methods = methods;
        // this class's static environment extends the superclass environment
        staticEnv = superClass.env();
        staticEnv = staticEnv.extendEnvRef(new Bindings());
        // initially create bindings for these static symbols ...
        Ref ref = new ValRef(new ObjectVal(localEnv));
        staticEnv.add(new Binding("!@", ref)); // not a var
        staticEnv.add(new Binding("myclass", new ValRef(this)));
        staticEnv.add(new Binding("superclass", new ValRef(superClass)));
        // The static RHS expressions are evaluated in the modified
        // staticEnv that includes the bindings for !@, myclass, superclass.
        // New static bindings are added as they are created,
        // as in top-level defines
        statics.addStaticBindings(staticEnv);
    }

    public Env env() {
        return staticEnv;
    }

    public ObjectVal makeObject(Ref objRef) {
        // System.err.println("... in makeObject ...");
        // create the parent object first (recursively)
        ObjectVal parent = superClass.makeObject(objRef);

        // this object's environment extends the parent object's environment
        Env env = parent.objectEnv;

        // add this class's static bindings (including those for myclass, etc)
        env = env.extendEnvRef(staticEnv.getBindings());

        // the fields come next
        Bindings fieldBindings = new Bindings();
        env = env.extendEnvRef(fieldBindings);
        // bind all of this object's instance fields to nil
        fields.addFieldBindings(fieldBindings);

        // bind all this object's methods as in letrec
        env = methods.addMethodBindings(env);

        // create the object
        ObjectVal objectVal = new ObjectVal(env);

        // bind 'super' field to the parent object
        fieldBindings.add("super", new ValRef(parent)); // parent object
        // bind 'self' field to the base object being created
        // (to speed up lookups)
        fieldBindings.add("self", objRef); // deep
        // bind 'this' field to this object environment
        fieldBindings.add("this", new ValRef(objectVal)); // shallow
        return objectVal;
    }

    public String toString() {
        return "class";
    }

}