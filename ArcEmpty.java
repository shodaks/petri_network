public class ArcEmpty extends InArc {

    public ArcEmpty(int weight, Place place) {
        super(weight, place);
    }

    @Override 
    public boolean isActive(){
        return true;
    }
    
}