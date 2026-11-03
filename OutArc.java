public class OutArc extends Arc {

    public OutArc(String name, int weight, Place place) {
        super(name, weight, place);
    }
    
    public void step() {
        this.getPlace().addToken(this.getWeight());
    }

    public String toString() {
        return "Arc sortant (" + this.getName() + ") : " + this.getWeight();
    }
}
