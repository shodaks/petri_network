public class ArcZero extends InArc {

    public ArcZero(int weight, Place place) {
        super(weight, place);
    }

    @Override 
    public boolean isActive(){
        return this.getPlace().getToken() == 0;
    }
    
}
