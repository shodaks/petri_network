public class InArc extends Arc{

    private boolean active;

    public InArc(int weight, Place place) {
        super(weight, place);
    }

    public boolean isActive(){
        return this.active;
    }
    
}
