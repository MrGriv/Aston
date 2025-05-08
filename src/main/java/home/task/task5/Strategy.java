package home.task.task5;

interface CallStrategy {
    void call(String str);
}

class LoudCall implements CallStrategy {

    @Override
    public void call(String str) {
        System.out.println(str.toUpperCase());
    }
}

class QuietCall implements CallStrategy {

    @Override
    public void call(String str) {
        System.out.println(str.toLowerCase());
    }
}

public class Strategy {
    private CallStrategy callStrategy;

    public void setCallStrategy(CallStrategy callStrategy) {
        this.callStrategy = callStrategy;
    }

    public void execute(String str) {
        callStrategy.call(str);
    }
}

class StrategyTest {
    public static void main(String[] args) {
        Strategy context = new Strategy();

        context.setCallStrategy(new LoudCall());
        context.execute("make loud");

        context.setCallStrategy(new QuietCall());
        context.execute("MAKE QUIET");
    }
}
