package com.traino.app;

import java.util.ArrayList;
import java.util.List;

public class Sporter {

    private int id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private String phone;
    private boolean verified;
    private double weight;
    private double length;
    private double bmi;
    private double fat;
    private String bloodtype;
    private List<Goal> goals;

    public Sporter(int id, String name, String surname, String username, String password, String email, String phone, boolean verified, double weight, double length, double fat, String bloodtype) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.verified = verified;
        this.weight = weight;
        this.length = length;
        this.bmi = calculateBmi();
        this.fat = fat;
        this.bloodtype = bloodtype;
        this.goals = new ArrayList<>();
    }

    public double calculateBmi(){
        return weight / (length/100 * length/100);
    }

    public int getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {

        return surname;
    }

    public double getWeight() {

        return weight;
    }

    public double getLength() {

        return length;
    }

    public double getBmi() {

        return bmi;
    }

    public double getFat() {

        return fat;
    }

    public void setGoals(List<Goal> goals) {

        this.goals = goals;
    }

    //Use case 1
    //Doel invoeren
    public void addGoal(Goal goal) {
        goals.add(goal);
    }


    public List<Goal> getGoals(){

        return goals;
    }

    @Override
    public String toString() {
        return "models.Sporter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}

