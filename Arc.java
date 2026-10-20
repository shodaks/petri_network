public class Arc{

    private int weight;
    private Place place;

    public Arc(int weight, Place place) {
        this.weight = weight;
        this.place = place;
    }

    public int getWeight() {
        return this.weight;
    }

    public Place getPlace() {
        return this.place;
    }

    public void setWeight(int newWeight) {
        this.weight = newWeight;
    }
    
}
