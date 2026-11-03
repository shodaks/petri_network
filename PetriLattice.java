import java.util.ArrayList;

public class PetriLattice implements PetriLatticeInterface{

    private ArrayList<Transition> transitions;

    public PetriLattice() {
        transitions = new ArrayList<>();
    }

    @Override
    public Transition addTransition() {
        Transition t = new Transition();
        this.transitions.add(t);
        return t;
    }

    @Override
    public void removeTransition(Transition transition) {
        this.transitions.remove(transition);
    }

    public String toString() {
        String out = "";
        for (Transition t : transitions) {
            for (InArc inarc : t.getInArcs()) {
                out += (inarc.getPlace().toString() + " -> " + inarc.toString() + "\n");
            }
            for (OutArc outarc : t.getOutArcs()) {
                out += (outarc.toString() + " -> " + outarc.getPlace().toString() + "\n");
            }
        }
        return out;
    }

    public void main() {
        PetriLattice PL1 = new PetriLattice();
        Transition t1 = PL1.addTransition();
        Place p1 = new Place(5);
        Place p2 = new Place(2);
        InArc arcin = new InArc(1, p1);
        t1.addInArc(arcin);
        OutArc arcout = new OutArc(10,p2);
        t1.addOutArc(arcout);
        System.out.println(PL1.toString());
        t1.step();
        System.out.println(PL1.toString());
    }
}
