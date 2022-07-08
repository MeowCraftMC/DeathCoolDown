package cx.rain.mc.deathcooldown.utility;

public class Pair<T, U> {
    private T leftValue;
    private U rightValue;

    public Pair(T left, U right) {
        leftValue = left;
        rightValue = right;
    }

    public T getLeft() {
        return leftValue;
    }

    public U getRight() {
        return rightValue;
    }
}
