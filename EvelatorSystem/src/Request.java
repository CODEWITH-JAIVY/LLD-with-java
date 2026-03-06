public class Request {
    private int  SourceFlore  ;
    private Directions directions  ;

    public Request( int sourceFlore , Directions direction  ) {
        this.directions = direction;
        SourceFlore = sourceFlore;
    }

    public int getSourceFlore() {
        return SourceFlore;
    }

    public void setSourceFlore(int sourceFlore) {
        SourceFlore = sourceFlore;
    }

    public Directions  getDirection() {
        return directions ;
    }

    public void setDirection(Directions  direction) {
        this.directions  = direction;
    }


}