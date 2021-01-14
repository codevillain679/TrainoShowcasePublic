package com.traino.app;

import com.traino.app.interfaces.Schedulable;

import java.util.ArrayList;
import java.util.List;

public class Sporter extends ScheduleItem {

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

    //getters are needed for uploading sporter information to db

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isVerified() {
        return verified;
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

    public String getBloodtype() {
        return bloodtype;
    }

    public double calculateBmi() {
        return Math.round(weight / (length / 100 * length / 100) * 100.00) / 100.00;
    }

    public String getProfileInfo() {
        String s = ("\n--- Profile ---" +
                "\nName:\t" + name + " " + surname + " (" + id + ")" +
                "\nLength:\t" + length +
                "\tWeight:\t" + weight +
                "\tFat: " + fat +
                "\nBMI:\t" + bmi +
                "\tBloodtype:\t" + bloodtype +
                "\tEmail:\t" + email);
        return s;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setWeight(double weight) {
        this.weight = weight;
        this.bmi = calculateBmi(); //calculate new bmi
    }

    public void setLength(double length) {
        this.length = length;
        this.bmi = calculateBmi(); //calculate new bmi
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    @Override
    protected Schedulable createSuggestion(Goal g) {
        Weekday day;
        //based on weight
        if (bmi < 20.0) { //superfit sporter should train on thursdays
            day = Weekday.THURSDAY;
        }
        if (weight < 25.0) { //regular sporter should train on friday
            day = Weekday.FRIDAY;
        } else { //fat sporter should train in his weekend. No free days for this one
            day = Weekday.SATURDAY;
        }

        Workout item = new Workout(0, "Workout for" + g.getTitle(), day, Status.ACTIVE);

        item.setAllExercises(g.getAllExercises()); //assign exercises

        return item;
    }

    public void edit(String name, String surname, String username, String password, String email, String phone){
        if(name != null) this.name = name;
        if(surname != null) this.surname = surname;
        if(username != null) this.username = username;
        if(password != null) {
            this.password = password;
            verify();
        }

        if(email != null) this.email = email;
        if(phone != null) this.phone = phone;
    }

    public void verify(){
        this.verified = true;
    }
}

