package Persistance;

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
    private Goal[] goals;

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
        this.goals = new Goal[0];
    }

    public Sporter(DataProvider<Sporter> sporterDataProvider){
        Sporter sporter = sporterDataProvider.get(1);
        if(sporter != null){
            this.id = sporter.id;
            this.name = sporter.name;
            this.surname = sporter.surname;
            this.username = sporter.username;
            this.password = sporter.password;
            this.email = sporter.email;
            this.phone = sporter.phone;
            this.verified = sporter.verified;
            this.weight = sporter.weight;
            this.length = sporter.length;
            this.bmi = sporter.bmi;
            this.fat = sporter.fat;
            this.bloodtype = sporter.bloodtype;
            this.goals = new Goal[0];
        }else{
            System.err.println("No sporter found oops");
        }
    }

    //Use case 1
    //Doel invoeren
    public void addGoal(Goal goal) {

    }


    //Use case 2
    //Doel verwijderen
    public void removeGoal(Goal goal){

    }

    //Use case 3
    //Workout toevoegen
    public void addWorkout(Workout workout){}


    //Use case 4
    //Workout verwijderen
    public void removeWorkout(Workout workout){}

    public Goal[] getGoals() {
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

