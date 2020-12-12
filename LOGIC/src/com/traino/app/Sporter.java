package com.traino.app;

import com.traino.app.interfaces.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class Sporter {

    private DataProvider<Sporter> sporterDataProvider;
    private DataProvider<Goal> goalDataProvider;

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
    private List<Goal> goal_list;

    private List<Goal> goals;

    public Sporter(int id, String name, List<Goal> goals){
        this.id = id;
        this.name = name;
        this.goals = goals;
    }

    public List<Goal> getGoals(){
        return goals;
    }

    public Sporter(int id, String name, String surname, String username, String password, String email, String phone, boolean verified, double weight, double length, double bmi, double fat, String bloodtype) {
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
        this.bmi = bmi;
        this.fat = fat;
        this.bloodtype = bloodtype;
        this.goal_list = new ArrayList<>();
    }

    public Sporter(DataProvider<Sporter> sporterDataProvider, DataProvider<Goal> goalDataProvider) {
        this.sporterDataProvider = sporterDataProvider;
        this.goalDataProvider = goalDataProvider;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public String getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    public void setGoals(List<Goal> goals) {
        this.goal_list = goals;
    }

    public boolean login(LoginBean loginBean){
        String[] params = {loginBean.getUsername(), loginBean.getPassword()};
        Sporter sporter = sporterDataProvider.get(params);
        if(sporter != null) {
            setId(sporter.getId());
            setName(sporter.getName());
            setSurname(sporter.getSurname());
            setUsername(sporter.getUsername());
            setPassword(sporter.getPassword());
            setEmail(sporter.getEmail());
            setPhone(sporter.getPhone());
            setVerified(sporter.isVerified());
            setWeight(sporter.getWeight());
            setLength(sporter.getLength());
            setBmi(sporter.getBmi());
            setFat(sporter.getFat());
            get_goal_list();
            return true;
        }else return false;
    }

    //Use case 1
    //Doel invoeren
    public void addGoal(Goal goal) {
        goal_list.add(goal);
    }

    //Use case 2
    //Doel verwijderen
    public void removeGoal(Goal goal){

    }

    //Use case 3
    //Workout toevoegen
    public void addWorkout(Workout workout){

    }


    //Use case 4
    //Workout verwijderen
    public void removeWorkout(Workout workout){

    }

    public List<Goal> getGoals2() {
        return goal_list;
    }

    @Override
    public String toString() {
        return "models.Sporter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public String getName() {
        return this.name;
    }

    public void get_goal_list() {
        String[] params = {getId()+""};
        List<Goal> goal_list = goalDataProvider.getAll(params);
        this.goal_list = goal_list;
    }

    public void update_goal_list(){

    }
}

