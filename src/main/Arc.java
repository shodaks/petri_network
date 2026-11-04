public abstract class Arc{

    private String name;
    private int weight;
    private Place place;

    public Arc(String name, int weight, Place place) {
        this.name = name;
        if (weight < 0) {
            throw new IllegalArgumentException("weight must be non-negative"); // Ajouté suite aux remarques de l'autre groupe
        }
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
        if (newWeight < 0) {
            throw new IllegalArgumentException("weight must be non-negative"); // Ajouté suite aux remarques de l'autre groupe
        }
        this.weight = newWeight;
    }
    
}
