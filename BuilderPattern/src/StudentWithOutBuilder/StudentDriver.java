package WithOutBuilder;

public class StudentDriver {

    public static void main(String[] args) {
        //   public StudentBuilder(String name, int age, int sem, double marks)
        StudentBuilder studentBuilder  = new StudentBuilder( "Sanjeet kumar" , 25 , 6 ,99.9) ;
        System.out.println(studentBuilder);
    }
}