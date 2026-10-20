import java.util.ArrayList;

public interface PetriLatticeInterface {

    public Place addPlace(int token);

    public Transition addTransition();

    public Arc addInArc(int weight, Place place);

    public Arc addArcZero(int weight, Place place);

    public Arc addArcEmpty(int weight, Place place);

    public Arc addOutArc(int weight, Place place);

    public void removeArc(Arc arc);

    public void removePlace(Place place);

    //public void addToken(int incrementToken, Place place);

    //public void removeToken(int incrementToken, Place place);

    public void removeTransition(Transition transition);

    //public void step(Transition transition);

    public interface Place {
    
        public int getToken();

        public void addToken(int incrementToken);

        public void removeToken(int incrementToken);
        
    }

    public interface Arc {
    
        public int getWeight();

        public Place getPlace();

        public void setWeight(int newWeight);
        
    }

    public interface Transition {

        public boolean isStepable();

        public void step();

        public ArrayList<InArc> getInArcs();

        public ArrayList<OutArc> getOutArcs();
        
    }
    
}