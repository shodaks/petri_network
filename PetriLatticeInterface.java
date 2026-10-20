public interface PetriLatticeInterface {

    // public Place addPlace(int token);

    public Transition addTransition();

    // public Arc addInArc(int weight, Place place);

    // public Arc addArcZero(int weight, Place place);

    // public Arc addArcEmpty(int weight, Place place);

    // public Arc addOutArc(int weight, Place place);

    // public void removeArc(Arc arc);

    // public void removePlace(Place place);

    //public void addToken(int incrementToken, Place place);

    //public void removeToken(int incrementToken, Place place);

    public void removeTransition(Transition transition);

    //public void step(Transition transition);
}