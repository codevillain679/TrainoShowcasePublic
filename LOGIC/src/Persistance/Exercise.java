package Persistance;

public class Exercise {
    private int id;
    private String name;
    private int amount;
    private String symbol;

    public Exercise(int id, String name, int amount, String symbol) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
