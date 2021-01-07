package com.traino.view;

import com.traino.app.Exercise;
import com.traino.app.Status;
import com.traino.app.Weekday;
import com.traino.app.Workout;
import com.traino.app.interfaces.Schedulable;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ScheduleView {

    private Scanner scanner;

    public ScheduleView(){
        scanner = new Scanner(System.in);
    }

    public void showSchedule(List<Schedulable> schedule){
        System.out.println("--- Schedule ---");
        for(Schedulable item : schedule){
            System.out.println(item.getScheduleInfo());
        }
    }

    public int viewScheduleOptions(){
        System.out.println("1.\tAdd workout\n2.\tEdit workout\n3.\tView Profile\n");

        int num = scanner.nextInt(); scanner.nextLine();

        return num;
    }

    public Workout addWorkout(){
        System.out.print("--- Add workout ---\nWorkout name:\t");
        String activity = scanner.nextLine();
        Weekday day = selectDay();
        Status status = Status.ACTIVE;

        Workout workout = new Workout(0, activity, day, status);

        System.out.println("You have to add at least one exercise");

        String input = "y";

        while(input.equals("y")){
            Exercise exercise = addExercise();
            workout.addExercise(exercise);
            System.out.print("Add another exercise? (Y/N)\t");
            input = scanner.nextLine().toLowerCase();
        }

        return workout;
    }

    public Weekday selectDay(){
        System.out.println("--- Select day ---");
        int n = 1;
        for(Weekday day : Weekday.values()){
            System.out.println(n + "\t" + day.toString().toLowerCase());
            n++;
        }
        System.out.print("Enter choice:\t");
        int selection = scanner.nextInt(); scanner.nextLine();
        Weekday selected = (Weekday) Arrays.stream(Weekday.values()).toArray()[selection-1];
        return selected;
    }

    public Exercise addExercise() {
        System.out.print("--- Add exercise ---\nWhat would you like to do? E.g. push-ups, chin-ups, etc..:\t");
        String name = scanner.nextLine();
        System.out.print("Rep amount:\t");
        int reps = scanner.nextInt();scanner.nextLine();
        System.out.print("Set amount:\t");
        int sets = scanner.nextInt(); scanner.nextLine();
        String symbol = "";
        if(name.length() > 2){
            symbol = name.substring(0,2);
        }
        System.out.println("Abbrev.:\t"+symbol);
        Exercise exercise = new Exercise(0, name, reps, sets, symbol);
        return exercise;
    }
}
