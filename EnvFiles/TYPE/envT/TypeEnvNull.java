package envT;

public class TypeEnvNull extends TypeEnv {

    // create an environment 
    public TypeEnvNull() {
    }
    
    public String toString(int n) {
	return n + ":\n";
    }

    public String toString() {
	return "\n";
    }
}