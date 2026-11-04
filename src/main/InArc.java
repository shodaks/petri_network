public class InArc extends Arc{

    public InArc(String name, int weight, Place place) {
        super(name, weight, place);
    }

    public boolean isActive(){
        return this.getPlace().getToken() >= this.getWeight();
    }
    
    public void step() {
        if (this.getPlace().getToken() < this.getWeight()) {
            throw new IllegalArgumentException("Exigence 7 : retirer plus de jetons que la place n'en contient ne doit pas la rendre négative");
        }
        this.getPlace().removeToken(this.getWeight());
    }
    
    public String toString() {
        return "Arc entrant (" + this.getName() + ") : " + this.getWeight();
    }
}
