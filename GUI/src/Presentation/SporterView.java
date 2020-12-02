package Presentation;

import Persistance.Goal;
import Persistance.Sporter;

public class SporterView {
    Sporter sporter;

    public SporterView(Sporter sporter){
        this.sporter = sporter;
    }

    public void displayProfile() {
        System.out.println(sporter.toString());
    }

    public void displayGoals(){
        Goal[] goal_list = sporter.getGoals();
    }
}
