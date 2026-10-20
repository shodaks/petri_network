import java.util.ArrayList;

public interface PetriLatticeInterface {

    public Place addPlace(int token);

    public Transition addTransition();

    public Arc addInArc(int weight, Place place, Transition transition);

    public Arc addArcZero(int weight, Place place, Transition transition);

    public Arc addArcEmpty(int weight, Place place, Transition transition);

    public Arc addOutArc(int weight, Place place, Transition transition);

    public void removeArc(Arc arc, Transition transition);

    public void removePlace(Place place, Arc arc);

    //public void addToken(int incrementToken, Place place);

    //public void removeToken(int incrementToken, Place place);

    public void removeTransition(Transition transition);

    //public void step(Transition transition);    
}