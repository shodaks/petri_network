import java.util.ArrayList;

public class Transition implements PetriLatticeInterface.Transition{

    private ArrayList<OutArc> outArcs;
    private ArrayList<InArc> inArcs;

    public Transition() {
        this.outArcs = new ArrayList<>();
        this.inArcs = new ArrayList<>();
    }

    @Override
    public boolean isStepable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isStepable'");
    }

    @Override
    public void step() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'step'");
    }

    @Override
    public ArrayList<InArc> getInArcs() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInArcs'");
    }

    @Override
    public ArrayList<OutArc> getOutArcs() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOutArcs'");
    }
    
}
