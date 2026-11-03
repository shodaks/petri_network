public class ArcZero extends InArc {

    public ArcZero(String name, int weight, Place place) {
        super(name, weight, place);
    }

    @Override 
    public boolean isActive(){
        return this.getPlace().getToken() == 0;
    }
    
}
