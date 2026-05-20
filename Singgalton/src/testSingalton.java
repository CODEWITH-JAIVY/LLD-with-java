public class testSingalton  {
    private final Singalton singalton   ;


    public testSingalton(Singalton singalton) {
        this.singalton = singalton;
    }

    public void  valueCheck() {
        System.out.println(singalton.getValue1());
        System.out.println(singalton.getValue2());

    }

    public static void main(String[] args) {
        testSingalton test  = new testSingalton(Singalton.getInstance()) ;
        test.valueCheck()  ;

    }
}