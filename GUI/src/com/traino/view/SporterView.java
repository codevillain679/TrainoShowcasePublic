package com.traino.view;

import com.traino.app.Goal;
import com.traino.app.LoginBean;
import com.traino.app.Sporter;
import com.traino.app.Tag;

import java.util.ArrayList;
import java.util.Scanner;

public class SporterView {
    Sporter sporter;
    Scanner scanner = new Scanner(System.in);

    public SporterView(Sporter sporter){
        this.sporter = sporter;
        System.out.println("----- TRAINO - Your Workout Scheduler -----");
    }

    public LoginBean viewLogin(){
        System.out.print("Login\nUsername:\t[nkorporaal]\nPassword:\t[********]\n"); // For testing
//        System.out.println("Login\nUsername:\t");
//        String username = scanner.nextLine();
//        System.out.print("Password:\t");
//        String password = scanner.nextLine();
//        LoginBean loginBean = new LoginBean(username, password);
        LoginBean loginBean = new LoginBean("nkorporaal", "traino213");
        return loginBean;
//        if(sporter.login(loginBean)){
//            System.out.println("Login succesful");
//        }else{
//            System.out.println("Login Failed. Username and password do not match");
//        }
    }

    public void viewProfile() {
        System.out.println("\n--- Profile ---" +
                "\nName:\t" + sporter.getName() + " " + sporter.getSurname() + " ("+ sporter.getId() +")" +
                "\nLength:\t"+sporter.getLength() +
                "\tWeight:\t"+sporter.getWeight() +
                "\tFat: "+sporter.getFat()+
                "\nBMI:\t"+sporter.getBmi());
    }

    public void viewGoals(){
        System.out.println("\n--- Goals ---" +
                "\n" + sporter.getGoals());
    }

    public void viewMenu(){
        System.out.println("\n--- Menu options ---\n1. Add goal\n2. Log out");
        int n = scanner.nextInt();
        scanner.nextLine();
        System.out.println("You selected: " + n);
        switch(n){
            case 1: //Add goal
                System.out.print("\n--- Add goal ---\nTitle:\t");
                String title = scanner.nextLine();
                System.out.print("Tag:\t #");
                String tag = "#" + scanner.nextLine();
                System.out.print("Description:\t");
                String description = scanner.nextLine();
                Goal goal = new Goal(title,description, new Tag(tag), new ArrayList<>());
                sporter.addGoal(goal);
                System.out.print("Goal added:\t"+sporter.getGoals());

                System.out.print("\n--- Add exercise ---");
                break;
            case 2: //Log out
                break;
        }
    }

    public void editProfile() {

    }
}
