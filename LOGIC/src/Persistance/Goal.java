package Persistance;

public class Goal extends Exercise {
    private int id;
    private String title;
    private String tag;
    private String description;

    public Goal(int id, String title, String tag, String description) {
        super(4, "Diamond Pushup", 1000, "dp");
        this.id = id;
        this.title = title;
        this.tag = tag;
        this.description = description;
    }

    public Goal(DataProvider<Goal> goalDataProvider){
        super(4, "Diamond Push-up", 1000, "dp");
        Goal goal = goalDataProvider.get(1);
        if(goal != null){
            this.id = goal.id;
            this.title = goal.title;
            this.tag = goal.tag;
            this.description = goal.description;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "models.Goal{" +
                "title='" + title + '\'' +
                ", tag='" + tag + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
