package vn.techzen.academy_pnv_12.model;

public class Student {
    private int id;
    private String name;
    private int score;

    // Constructor không tham số
    public Student() {}

    // Constructor có tham số
    public Student(int id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    // Getter và setter cho id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter và setter cho name
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter và setter cho score
    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
