public class OutArc extends Arc {

    public OutArc(int weight, Place place) {
        super(weight, place);
    }
    
    public void step() {
        this.getPlace().addToken(this.getWeight());
    }

    public String toString() {
        return "Arc sortant : " + this.getWeight();
    }
}
