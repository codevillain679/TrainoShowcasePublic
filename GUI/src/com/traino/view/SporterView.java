package com.traino.view;

import com.traino.app.*;

import java.util.*;

public class SporterView {
    private Scanner scanner = new Scanner(System.in);

    public SporterView(){
        System.out.println("----- TRAINO - Your Workout Scheduler -----");
    }

    public LoginBean viewLogin(){
        System.out.print("Login\nUsername:\t[nkorporaal]\nPassword:\t[********]\n"); // For testing
//        System.out.println("Login\nUsername:\t");
//        String username = scanner.nextLine();
//        System.out.print("Password:\t");
//        String password = scanner.nextLine();
//        LoginBean loginBean = new LoginBean(username, password);
        LoginBean loginBean = new LoginBean("dushi", "purr");
        return loginBean;
    }

    public void viewProfile(Sporter sporter) {
        System.out.println("\n--- Profile ---" +
                "\nName:\t" + sporter.getName() + " " + sporter.getSurname() + " ("+ sporter.getId() +")" +
                "\nLength:\t"+ sporter.getLength() +
                "\tWeight:\t"+ sporter.getWeight() +
                "\tFat: "+ sporter.getFat()+
                "\nBMI:\t"+ sporter.getBmi());

        for(Goal goal : sporter.getGoals()){
            showGoal(goal);
        }
    }

    public int selectMenuOption(){
        System.out.println("\n--- Menu options ---\n1.\tAdd goal\n2.\tEdit goal\n3.\tAdd Workout\n4.\tView Schedule in Excel\n5.\tGet suggestion for workouts\n9. Logout");
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

    public Goal viewGoals(Sporter sporter){
        System.out.println("\n--- Goals ---" +
                "\n" + sporter.getGoals());
        return null;
    }


    public Goal selectGoal(Sporter sporter) {
        List<Goal> allGoals = sporter.getGoals();
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

    public void editProfile() {

    }

    public Goal addGoal(){
        System.out.print("--- Add goal ---\nActivity:\t");
        String activity = scanner.nextLine();
        System.out.print("Description:\t");
        String description = scanner.nextLine();
        System.out.print("Tag:\t#");
        String link = scanner.nextLine();
        Tag goalTag = new Tag(link);

        System.out.println("You have to set at least one exercise for this goal");

        //TODO: Add exercise(s) here
        Exercise exercise = addExercise();

        Goal goal = new Goal(0, activity,description,goalTag, new ArrayList<>());

        goal.addExercise(exercise);

        return goal;
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

    public int selectGoalMenuOption(Goal goal) {
        showGoal(goal);

        System.out.println("--- Edit goal ---\n1.\tAdd exercise\n2.\tRemove goal");

        String input = scanner.nextLine();

        int num = parseInt(input);

        return num;
    }

    public void showGoal(Goal goal){
        System.out.println(goal.toString());

        for(Exercise exercise : goal.getAllExercises()){
            System.out.println("\t" + exercise.toString());
        }

        System.out.println();
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

    public boolean viewLogout() {
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

    public int parseInt(String s){
        boolean isNumeric = s.chars().allMatch( Character::isDigit );
        if(isNumeric) {
            int n = Integer.parseInt(s);
            return n;
        }else{
            return -1;
        }
    }

    public Workout selectWorkout(List<Workout> allWorkouts){
        Workout selectedWorkout = null;
        int n = 1;
        for(Workout workout : allWorkouts){
            showWorkout(workout);
        }
        System.out.print("Enter choice:\t");
        int selection = scanner.nextInt(); scanner.nextLine();

        selectedWorkout = allWorkouts.get(selection - 1);

        return selectedWorkout;
    }

    public void showWorkout(Workout workout){
        System.out.println(workout.toString());

        for(Exercise exercise : workout.getAllExercises()){
            System.out.println("\t" + exercise.toString());
        }

        System.out.println();
    }

    public int selectLoginOption() {
        System.out.println("1.\tRegister\n2.\tLogin");
        String input = scanner.nextLine();

        int num = parseInt(input);

        return num;
    }

    public Sporter viewRegister() {
        Sporter sporter = null;

        //public Sporter(int id, String name, String surname, String username, String password, String email, String phone, boolean verified, double weight, double length, double fat, String bloodtype) {

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
}
