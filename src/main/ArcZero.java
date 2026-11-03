public class ArcZero extends InArc {

    public ArcZero(String name, Place place) {
        super(name, 0, place);
    }

    @Override 
    public boolean isActive(){
        return this.getPlace().getToken() == 0;
    }
    
}
