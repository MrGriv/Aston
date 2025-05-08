package home.task.task5;

public abstract class Chain {
    protected Chain nextChain;

    public void setNext(Chain nextChain) {
        this.nextChain = nextChain;
    }

    public abstract void determineInterval(int number);
}

class FirstInterval extends Chain {
    @Override
    public void determineInterval(int number) {
        if (number >= 0 && number <= 10) {
            System.out.println("Число в первом интервале");
        } else if (nextChain != null) {
            nextChain.determineInterval(number);
        }
    }
}

class SecondInterval extends Chain {
    @Override
    public void determineInterval(int number) {
        if (number > 10) {
            System.out.println("Число во втором интервале");
        } else if (nextChain != null) {
            nextChain.determineInterval(number);
        }
    }
}

class ThirdInterval extends Chain {
    @Override
    public void determineInterval(int number) {
        if (number < 0) {
            System.out.println("Число в третьем интервале");
        } else if (nextChain != null) {
            nextChain.determineInterval(number);
        }
    }
}

class ChainTest {
    public static void main(String[] args) {
        FirstInterval firstInterval = new FirstInterval();
        SecondInterval secondInterval = new SecondInterval();
        ThirdInterval thirdInterval = new ThirdInterval();

        firstInterval.setNext(secondInterval);
        secondInterval.setNext(thirdInterval);

        firstInterval.determineInterval(-1);
    }
}
