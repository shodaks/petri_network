public abstract class Arc{

    private String name;
    private int weight;
    private Place place;

    public Arc(String name, int weight, Place place) {
        this.name = name;
        this.weight = weight;
        this.place = place;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setPlace(Place newPlace) {
        this.place = newPlace;
    }

    public Place getPlace() {
        return this.place;
    }

    public void setWeight(int newWeight) {
        this.weight = newWeight;
    }
    
}
