package env;

public interface IToken {
    public abstract String toString();

    public abstract String errString();

    public abstract boolean isEOF();
}
