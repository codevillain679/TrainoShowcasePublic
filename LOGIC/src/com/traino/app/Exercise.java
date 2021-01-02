package com.traino.app;

public class Exercise {
    private int id;
    private String name;
    private int reps;
    private int sets;
    private String symbol;

    public Exercise(int id, String name, int reps, int sets, String symbol) {
        this.id = id;
        this.name = name;
        this.reps = reps;
        this.sets = sets;
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

        return "-\t" + name + " (" + reps + "x, " + sets + " sets)";
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }
}
