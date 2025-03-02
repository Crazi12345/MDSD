package main.metamodel;

import java.util.List;
import java.util.ArrayList;

public class State {

    private String name;
    private List<Transition> transitions;

    public State(String name) {

        this.name = name;
        transitions = new ArrayList<Transition>();
    }

    public Object getName() {
        return name;
    }

    public List<Transition> getTransitions() {

        return transitions;
    }

    public void AddTransition(Transition ts) {
        transitions.add(ts);
    }

    public Transition getTransitionByEvent(String string) {
        for (int i = 0; i < transitions.size(); i++) {
            if (transitions.get(i).getEvent().equals(string)) {
                return transitions.get(i);
            }
        }
        return null;
    }
}
