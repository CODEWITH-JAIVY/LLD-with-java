package WithOutBuilder;

public class StudentBuilder {
    private String name  ;
    private  int age  ;
    private  int sem ;
    private  double marks  ;

    public StudentBuilder(String name, int age, int sem, double marks) {
        this.name = name;
        this.age = age;
        this.sem = sem;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "StudentBuilder{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sem=" + sem +
                ", marks=" + marks +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSem() {
        return sem;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }
}