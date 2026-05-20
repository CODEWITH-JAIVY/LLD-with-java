package WithBuilderPattern;

public class StudentBuilder1 {
    private String name  ;
    private  int age  ;
    private  int sem  ;
    private  double marks  ;

    StudentBuilder1(Builder builder) {
       this.name   = builder.name ;
       this.age  = builder.age ;
       this.sem  = builder.sem  ;
       this.marks  = builder.marks  ;
    }


    // Optional: to print values
    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Sem: " + sem);
        System.out.println("Marks: " + marks);
    }


    public static class Builder {
        private String name  ;
        private  int age  ;
        private  int sem  ;
        private  double marks  ;

        public Builder name  (String name ) {
            this.name = name  ;
            return  this  ;
        }
        public Builder age  (int age  ) {
            this.age = age  ;
            return  this  ;
        }
        public Builder sem ( int sem ) {
            this.sem = sem  ;
            return  this  ;
        }
        public  Builder marks(  int marks  ) {
            this.marks   = marks  ;
            return  this  ;
        }
        public StudentBuilder1 build () {
             return  new StudentBuilder1(this) ; 
        }
    }


}