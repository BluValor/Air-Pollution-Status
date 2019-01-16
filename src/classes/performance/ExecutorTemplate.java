package classes.performance;

public abstract class ExecutorTemplate {

    abstract void initialize(String[] args);
    abstract void setContext();
    abstract void perform();
    abstract void putOut();

    public final void execute(String[] args) {
        initialize(args);
        setContext();
        perform();
        putOut();
    }
}
