package WithBuilderPattern;

public class sutudent {
    public static void main(String[] args) {
        StudentBuilder1 studentBuilder1  =  new StudentBuilder1.Builder()
                .age(23)
                .name("Sanjeet ")
                .sem(6)
                .marks((int) 99.9)
                .build() ;
        studentBuilder1.display();
    }

}