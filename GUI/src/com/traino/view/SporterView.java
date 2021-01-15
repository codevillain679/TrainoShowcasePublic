package com.traino.view;

import com.traino.app.*;
import com.traino.app.admin.SporterAdmin;
import com.traino.app.interfaces.Schedulable;

import java.util.*;

public class SporterView {
    private static Scanner scanner = new Scanner(System.in);
    private static SporterAdmin controller;
    private static Sporter sporter;
    private static ScheduleView scheduleView;

    public SporterView(SporterAdmin controller){
        this.controller = controller;
        this.scheduleView = new ScheduleView();
    }

    public static void startApplication(){

        System.out.println("----- TRAINO - Your Workout Scheduler -----");

        showMainMenu();

        showProfileMenu();
    }

    private static void showMainMenu(){
        int menuLoginOption = selectLoginOption();

        switch (menuLoginOption) {
            case 1: //REGISTER
                while (controller.getLoggedInSporter() == null) {
                    Sporter newSporter = viewRegister();

                    //Upload new user to database
                    controller.addSporter(newSporter);

                    //Login
                    newSporter = controller.login(new LoginBean(newSporter.getUsername(), newSporter.getPassword()));

                    //Show profile
                    viewProfile(newSporter);
                }
                break;
            case 2: //LOGIN
                while (controller.getLoggedInSporter() == null) {
                    sporter = controller.login(viewLogin());
                }

                //Show profile
                viewProfile(sporter);
                break;
            case 3:
                //EXIT APPLICATION
                System.out.println("Application closed");
                System.exit(0);
                break;
        }
    }

    private static void showProfileMenu(){
        int menuOption = selectMenuOption();

        while (menuOption != -1) {
            switch (menuOption) {
                case 0:
                    //under development
                    break;
                case 1:
                    //EDIT PROFILE
                    Sporter sporter_edit = editProfile(sporter);
                    controller.updateSporter(sporter_edit);
                    viewProfile(sporter_edit);

                    break;
                case 2:
                    //ADD GOAL
                    Goal goal = addGoal();

                    // Add goal to database
                    controller.addGoal(goal);
                    break;
                case 3: //EDIT GOAL
                    //Retrieve goals from database
                    List<Goal> allGoals = controller.getAllGoals(sporter);

                    //Select goal
                    Goal edit_goal = selectGoal(allGoals);

                    //Edit action
                    int goalEditMenuOption = selectGoalMenuOption(edit_goal);

                    switch (goalEditMenuOption) {
                        case 1: //ADD EXERCISE
                            //Create exercise
                            Exercise exercise = addExercise();

                            //Add exercise to goal exercise list
                            edit_goal.getAllExercises().add(exercise);

                            //Add exercise to database
                            controller.addGoalExercise(edit_goal, exercise);
                            break;
                        default:
                            System.out.println("Invalid input");
                            break;
                    }

                    //TODO: implement required methods for 'edit' in datastore
                    break;
                case 4: //VIEW SCHEDULE
                    List<Schedulable> schedule = new ArrayList<>();

                    List<Goal> allGoals4 = controller.getAllGoals(sporter);

                    for(Goal goalitem : allGoals4){
                        schedule.addAll(goalitem.getScheduleItems());
                    }

                    scheduleView.showSchedule(schedule);

                    int scheduleOption = scheduleView.viewScheduleOptions();

                    switch(scheduleOption){
                        case 1: //ADD WORKOUT

                            //--- show suggestion
                            List<Goal> allGoals3 = controller.getAllGoals(sporter);

                            Goal selectedGoal2 = selectGoal(allGoals3);

                            Workout suggestion = (Workout) controller.createSuggestion(selectedGoal2);

                            Workout workout = scheduleView.addWorkout(suggestion);

                            controller.addWorkout(workout, selectedGoal2);

                            List<Schedulable> schedule_new = new ArrayList<>();

                            List<Goal> allGoals_new = controller.getAllGoals(sporter);

                            for(Goal goalitem_new : allGoals_new){
                                schedule_new.addAll(goalitem_new.getScheduleItems());
                            }

                            scheduleView.showSchedule(schedule_new);
                            break;
                        case 2:
                            //edit workout


                            break;
                        case 3:
                            //Show Profile
                            viewProfile(sporter);
                            break;
                    }

                    break;
                case 5: //LOGOUT
                    //Ask for confirm
                    boolean confirm = viewLogout();

                    //Clear sporter session
                    if (confirm) {
                        controller.logout();
                        //showMainMenu();
                    } else {
                        viewProfile(sporter);
                    }
                    break;
            }
            menuOption = selectMenuOption();
        }
    }

    public static LoginBean viewLogin(){
        System.out.print("Login\nUsername:\t[nkorporaal]\nPassword:\t[********]\n"); // For testing
//        System.out.println("Login\nUsername:\t");
//        String username = scanner.nextLine();
//        System.out.print("Password:\t");
//        String password = scanner.nextLine();
//        LoginBean loginBean = new LoginBean(username, password);
        LoginBean loginBean = new LoginBean("nkorporaal", "traino213");
        return loginBean;
    }

    public static void viewProfile(Sporter sporter) {
        System.out.println(sporter.getProfileInfo());

        System.out.println("\n--- Goals ---");
        for(Goal goal : sporter.getGoals()){
            showGoal(goal);
        }
    }

    public static int selectMenuOption(){
       // System.out.println("\n--- Menu options ---\n1.\tAdd goal\n2.\tEdit goal\n3.\tAdd Workout\n4.\tView Schedule\n5.\tGet suggestion for workouts\n6.\tEdit Profile\n9.\tLogout\n");

        System.out.println("--- Menu ---\n1.\tEdit Profile\n2.\tAdd goal\n3.\tEdit goal\n4.\tView Schedule\n5.\tLogout");

        String input = scanner.nextLine();
        boolean isNumeric = input.chars().allMatch( Character::isDigit );
        if(isNumeric) {
            int n = Integer.parseInt(input);
            System.out.println("You selected: " + n);
            return n;
        }else{
            System.out.println("Please enter a valid menu option");
            return 0;
        }
    }

    public static Goal selectGoal(List<Goal> allGoals) {
        System.out.println("--- Select goal ---");
        int n = 1;
        for(Goal goal: allGoals){
            System.out.println(n + ":\t" + goal.getTitle());
            n++;
        }
        System.out.print("Enter choice:\t");
        int selection = scanner.nextInt(); scanner.nextLine();
        Goal selected = allGoals.get(selection - 1);
        return selected;
    }

    public static Goal addGoal(){
        System.out.print("--- Add goal ---\nActivity:\t");
        String activity = scanner.nextLine();
        System.out.print("Description:\t");
        String description = scanner.nextLine();
        System.out.print("Tag:\t#");
        String link = scanner.nextLine();
        Tag goalTag = new Tag(link);

        while(activity == "" || description == "" || link == ""){
            System.out.print("Goal cannot be empty. Please fill in all the fields\nActivity:\t");
            activity = scanner.nextLine();
            System.out.print("Description:\t");
            description = scanner.nextLine();
            System.out.print("Tag:\t#");
            link = scanner.nextLine();
            goalTag = new Tag(link);
        }

        System.out.println("You have to set at least one exercise for this goal");

        //TODO: Add exercise(s) here
        Exercise exercise = addExercise();

        Goal goal = new Goal(0, activity,description,goalTag, new ArrayList<>());

        goal.addExercise(exercise);

        return goal;
    }

    public static Exercise addExercise() {
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

    public static int selectGoalMenuOption(Goal goal) {
        showGoal(goal);

        System.out.println("--- Edit goal ---\n1.\tAdd exercise\n2.\tRemove goal");

        String input = scanner.nextLine();

        int num = parseInt(input);

        return num;
    }

    public static void showGoal(Goal goal){
        System.out.println(goal.toString());

        for(Exercise exercise : goal.getAllExercises()){
            System.out.println("\t" + exercise.toString());
        }

        System.out.println();
    }

    public static boolean viewLogout() {
        System.out.print("Are you sure to logout? (Y/N)\t");

        String input = scanner.nextLine();

        if(input.toLowerCase().equals("n")){
            System.out.println("Logout cancelled");
            return false;
        }else{
            System.out.println("Logging out...");
            return true;
        }
    }

    public static int parseInt(String s){
        boolean isNumeric = s.chars().allMatch( Character::isDigit );
        if(isNumeric) {
            int n = Integer.parseInt(s);
            return n;
        }else{
            return -1;
        }
    }

    public static int selectLoginOption() {
        System.out.println("1.\tRegister\n2.\tLogin\n3.\tExit application");
        String input = scanner.nextLine();

        int num = parseInt(input);

        return num;
    }

    public static Sporter viewRegister() {
        Sporter sporter = null;
        System.out.print("--- Register ---\nUsername:\t");
        String username = scanner.nextLine();
        System.out.print("Password:\t");
        String password = scanner.nextLine();
        System.out.print("Name:\t\t");
        String name = scanner.nextLine();
        System.out.print("Surname:\t");
        String surname = scanner.nextLine();
        System.out.print("Email:\t\t");
        String email = scanner.nextLine();
        boolean verified = false; //verification
        System.out.print("Phone:\t\t");
        String phone = scanner.nextLine();
        System.out.print("Weight:\t\t");
        String str_weight = scanner.nextLine();
        double weight = Double.parseDouble(str_weight.replace(",", ".")); //replace comma with .
        System.out.print("Length:\t\t");
        String str_length = scanner.nextLine();
        double length = Double.parseDouble(str_length.replace(",", "."));
        System.out.print("Fat percentage:\t");
        String str_fat = scanner.nextLine();
        double fat = Double.parseDouble(str_fat.replace(",", "."));
        System.out.println("Bloodtype:\t");

        String[] types = new String[]{"A-", "A+", "B-", "B+", "AB-", "AB+", "O-", "O+"};

        for(int n = 1; n <= types.length; n++){
            System.out.println(n + ".\t" + types[n-1]);
        }

        System.out.println("Enter choice:\t");

        int selection = scanner.nextInt(); scanner.nextLine();

        String bloodtype = types[selection-1];

        sporter = new Sporter(0, name,surname, username, password, email, phone, verified, weight, length, fat, bloodtype);

        return sporter;
    }

    public static Sporter editProfile(Sporter sporter){
        System.out.println("--- Edit Profile ---\nWhat would you like to edit?\n1.\tName\n2.\tSurname\n3.\tUsername\n4.\tPassword\n5.\tEmail\n6.\tPhone\n7.\tWeight\n8.\tLength\n9.\tFat percentage\n10.\tBloodtype");

        String name = null;
        String surname = null;
        String username = null;
        String password = null;
        String email = null;
        String phone = null;

        int editOption = scanner.nextInt(); scanner.nextLine();
        switch(editOption){
            case 1:
                //Name
                System.out.print("Name:\t\t");
                 name = scanner.nextLine();
                break;
            case 2:
                //Surname
                System.out.print("Surname:\t");
                 surname = scanner.nextLine();
                break;
            case 3:
                //username
                System.out.println("Username:\t");
                 username = scanner.nextLine();
                break;
            case 4:
                //Password
                System.out.print("Password:\t");
                 password = scanner.nextLine();
                break;
            case 5:
                //Email
                System.out.print("Email:\t\t");
                 email = scanner.nextLine();
                break;
            case 6:
                //phone
                System.out.print("Phone:\t\t");
                 phone = scanner.nextLine();
                break;
            case 7:
                //Weight
                System.out.print("Weight:\t\t");
                String str_weight = scanner.nextLine();
                double weight = Double.parseDouble(str_weight.replace(",", "."));
                sporter.setWeight(weight);
                break;
            case 8:
                //Length
                System.out.print("Length:\t\t");
                String str_length = scanner.nextLine();
                double length = Double.parseDouble(str_length.replace(",", "."));
                sporter.setLength(length);
                break;
            case 9:
                //Fat percentage
                System.out.print("Fat percentage:\t");
                String str_fat = scanner.nextLine();
                double fat = Double.parseDouble(str_fat.replace(",", "."));
                sporter.setFat(fat);
                break;
            case 10:
                //Bloodtype
                String[] types = new String[]{"A-", "A+", "B-", "B+", "AB-", "AB+", "O-", "O+"};

                for(int n = 1; n <= types.length; n++){
                    System.out.println(n + ".\t" + types[n-1]);
                }

                System.out.println("Enter choice:\t");

                int selection = scanner.nextInt(); scanner.nextLine();

                String bloodtype = types[selection-1];

                sporter.setBloodtype(bloodtype);
                break;
            default: break;
        }

        sporter.edit(name, surname, username, password, email, phone);

        return sporter;
    }
}
