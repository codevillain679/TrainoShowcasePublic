package viewdemo;

import com.traino.app.*;

import java.util.*;

public class SporterViewDemo {

    Scanner scanner;

    public SporterViewDemo(){

        scanner = new Scanner(System.in);
    }

    public void showProfile(Sporter sporter){
        System.out.println(sporter.getProfileInfo());
        System.out.println("-- Goals --");
        for(Goal goal : sporter.getGoals()){
            showGoal(goal);
        }
    }

    public void showGoal(Goal goal){

        System.out.println(goal.getTitle());
        for(Exercise exercise : goal.getAllExercises()){
            showExercise(exercise);
        }
    }

    public LoginBean showLogin(){

        System.out.println("--- Login ---");
        System.out.print("Username:\t[nkorporaal]\nPassword:\t[********]\n\n"); // For testing
//        System.out.println("Login\nUsername:\t");
//        String username = scanner.nextLine();
//        System.out.print("Password:\t");
//        String password = scanner.nextLine();
//        LoginBean loginBean = new LoginBean(username, password);
        LoginBean loginBean = new LoginBean("nk", "admin");

        return loginBean;
    }

    public int showMenu(){

        System.out.println("\n--- Menu options ---\n1. Add goal\n2. Edit goal\n3. Add Workout\n4. View Schedule in Excel\n9. Logout");
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

    public Goal addGoal() {

        System.out.print("--- Add goal ---\nActivity:\t");
        String activity = scanner.nextLine();
        System.out.print("Description:\t");
        String description = scanner.nextLine();
        System.out.print("Tag:\t#");
        String link = scanner.nextLine();
        Tag goalTag = new Tag(link);

        System.out.println("You have to set at least one exercise for this goal");
        Exercise exercise = addExercise();

        Goal goal = new Goal(0, activity,description,goalTag, new ArrayList<>());

        goal.addExercise(exercise);

        return goal;
    }

    public Exercise addExercise(){

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

    public Workout addWorkout(){

        System.out.print("--- Add workout ---\nTitle:\t");
        String title = scanner.nextLine();
        System.out.println("You have to add at least one exercise");
        Exercise exercise = addExercise();
        Weekday day = selectDay();
        Status status = Status.ACTIVE;

        Workout workout = new Workout(0, title, day, status);
        workout.addExercise(exercise);

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

    public Goal selectGoal(List<Goal> allGoals) {

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

    public void showWorkout(Workout workout) {
        System.out.println(workout.getActivity());
        for(Exercise exercise: workout.getAllExercises()){
            showExercise(exercise);
        }
    }

    //is this necessary?
    public void showExercise(Exercise exercise) {
        System.out.println(exercise.toString());
    }

    public Exercise editGoal(Goal goal) {
        System.out.println("--- Edit goal ---");
        showGoal(goal);
        System.out.println("Please select an option below\n1. Add exercise");
        String input = scanner.nextLine();
        if(parseInt(input) == 1) {
            Exercise exercise = addExercise();
            return exercise;
        }
        return null;
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

    public Workout showSuggestions(List<Workout> suggestions) {
        if(suggestions.size() > 0){
            System.out.println("--- Suggestions ---");
            int n = 1;
            for(Workout workout : suggestions){
                System.out.println(n++ + ":\t" + workout.getActivity());
                for(Exercise exercise : workout.getAllExercises()){
                    System.out.println(exercise.toString());
                }
            }
            String input = scanner.nextLine();
            if(parseInt(input) != -1) {
                Workout workout_selected = suggestions.get(parseInt(input) - 1);
                System.out.println("You selected: " + workout_selected);
                Weekday day = selectDay();
                workout_selected.setDay(day);
                return workout_selected;
            }
        }
        return null;
    }
}
