package main.metamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Machine {
    private List<State> states;
    private State initial = null;
    private HashMap<String, Integer> vars;

    public Machine() {
        states = new ArrayList<State>();
        vars = new HashMap<String, Integer>();
    }

    public Machine(List<State> states) {
        this.states = states;
        vars = new HashMap<String, Integer>();
    }

    public List<State> getStates() {
        return states;
    }

    public State getInitialState() {
        return initial;
    }

    public void setInitialState(State init) {
        initial = init;
    }

    public State getState(String string) {
        for (int i = 0; i < states.size(); i++) {
            if (string == states.get(i).getName()) {
                return states.get(i);
            }

        }
        return null;
    }

    public void addVars(HashMap<String, Integer> vars) {

        this.vars = vars;
    }

    public HashMap<String, Integer> getVars() {
        return vars;
    }

    public int numberOfIntegers() {
        return vars.keySet().size();
    }

    public boolean hasInteger(String string) {
        return vars.containsKey(string);

    }
}
