public class InArc extends Arc{

    public InArc(String name, int weight, Place place) {
        super(name, weight, place);
    }

    public boolean isActive(){
        return true;
    }
    
    public void step() {
        this.getPlace().removeToken(this.getWeight());
    }
    
    public String toString() {
        return "Arc entrant : " + this.getWeight();
    }
}
