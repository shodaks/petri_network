import java.util.ArrayList;

public class Transition {

    private ArrayList<OutArc> outArcs;
    private ArrayList<InArc> inArcs;
    private String name;

    public Transition(String name) {
        this.name = name;
        this.outArcs = new ArrayList<>();
        this.inArcs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStepable() {
        for (InArc inarc : this.inArcs) {
            if (!inarc.isActive()) {
                return false;
            }
        }
        return true;
    }

    public void step() {
        if (this.isStepable()) {
            for (InArc inarc : this.inArcs) {
                inarc.step();
            }
            for (OutArc outarc : this.outArcs) {
                outarc.step();
            }
        }
    }

    public ArrayList<InArc> getInArcs() {
        return inArcs;
    }

    public ArrayList<OutArc> getOutArcs() {
        return outArcs;
    }

    public void addInArc(InArc inarc) {
        this.inArcs.add(inarc);
    }
    
    public void addOutArc(OutArc outarc) {
        this.outArcs.add(outarc);
    }
}
