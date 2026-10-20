public class Arc implements PetriLatticeInterface.Arc{

    private int weight;
    private Place place;

    public Arc(int weight, Place place) {
        this.weight = weight;
        this.place = place;
    }


    @Override
    public int getWeight() {
        return this.weight;
    }

    @Override
    public PetriLatticeInterface.Place getPlace() {
        return this.place;
    }

    @Override
    public void setWeight(int newWeight) {
        this.weight = newWeight;
    }
    
}
