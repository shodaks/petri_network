import java.util.ArrayList;

public class Transition {

    private ArrayList<OutArc> outArcs;
    private ArrayList<InArc> inArcs;

    public Transition() {
        this.outArcs = new ArrayList<>();
        this.inArcs = new ArrayList<>();
    }

    public boolean isStepable() {
        boolean _isStepable = true;
        int i = 0;
        while (i < this.inArcs.size() && _isStepable) {
            InArc arc = this.getInArcs().get(i);
            if (arc.isActive()) {
                int weight = arc.getWeight();
                int tokenPlace = arc.getPlace().getToken();
                _isStepable = weight < tokenPlace;
            }
            i++;
        }
        return _isStepable;
    }

    public void step() {
        if (this.isStepable()) {
            for (InArc inarc : this.inArcs) {
                if (inarc.isActive()) {
                    inarc.step();
                }
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
