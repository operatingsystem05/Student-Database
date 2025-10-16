import java.io.Serializable;

public class Student implements Serializable
{
    private String name;
    private long id;
    private int grade;

    public Student(String name, long id, int grade)
    {
        this.name = name;
        this.id = id;
        this.grade = grade;
    }

    @Override
    public String toString()
    {
        return "Student name: " + name + "\nStudent ID: " + id + "\nStudent grade: " + grade;
    }
}
