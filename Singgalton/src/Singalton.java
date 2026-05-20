public class Singalton {
    private   static   Singalton instance   ;

    private  int value1 = 10 ;
    private  int value2  = 15  ;
   private Singalton() {}

   public static  Singalton getInstance() {
      if(instance == null ) {
          synchronized (Singalton.class) {
              instance = new Singalton();
              System.out.println("Single instance is create ");
          }
      }
       System.out.println("Single Instance is create  " + instance );
      return instance  ;
   }

    public int getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }
}