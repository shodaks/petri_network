public interface PetriLatticeInterface {

    // public Place addPlace(int token);

    public Transition addTransition(String name);

    public Place addPlace(String name, int weight);

    public Arc addInArc(String name, int weight, Place place, Transition transition);

    public Arc addArcZero(String name, Place place, Transition transition);

    public Arc addArcEmpty(String name, Place place, Transition transition);

    public Arc addOutArc(String name,int weight, Transition transition, Place place);

    public void removeArc(Arc arc, Transition transition);

    public void removePlace(Place place);

    //public void addToken(int incrementToken, Place place);

    //public void removeToken(int incrementToken, Place place);

    public void removeTransition(Transition transition);

    //public void step(Transition transition);    
}