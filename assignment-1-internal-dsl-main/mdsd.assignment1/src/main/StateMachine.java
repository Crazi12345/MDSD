package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;

public class StateMachine {

    private ArrayList<State> states;
    private State initial = null;
    private HashMap<String, Integer> variables;

    public StateMachine() {

        states = new ArrayList<State>();
        variables = new HashMap<String, Integer>();
    }

    public StateMachine(ArrayList<State> states) {
        this.states = states;
    }

    public Machine build() {
        // I know it is more proper to add it all through the constructor, but this give
        // me more readability
        // Since this is not production code, i decided it was okay ;)
        Machine m = new Machine(states);
        m.setInitialState(initial);
        m.addVars(variables);

        // This is the accumulation of a lot bad decisions and i did not want to fix it,
        // its called the sunk cost fallacy.
        for (State state : states) {
            for (Transition transition : state.getTransitions()) {
                String targetStateName = transition.getPlaceholder();
                if (targetStateName != null) {
                    State targetState = getStateByID(targetStateName);
                    transition.setTarget(targetState);
                }
            }
        }
        return m;

    }

    public StateMachine state(String string) {
        State s = new State(string);
        states.add(s);
        return this;
    }

    public StateMachine initial() {

        int size = states.size();
        if (size > 0) {
            initial = states.get(size - 1);
        }
        return this;
    }

    public StateMachine when(String string) {

        int size = states.size() - 1;
        states.get(size).AddTransition(new Transition(string, null));
        return this;
    }

    public StateMachine to(String string) {
        // Welcome to a cursed way of doing this
        int size = states.size() - 1;
        int t_size = states.get(size).getTransitions().size() - 1;
        Transition tt = states.get(size).getTransitions().get(t_size);
        tt.setPlaceholder(string);
        return this;
    }

    private State getStateByID(String s) {
        for (int i = 0; i < states.size(); i++) {

            if (states.get(i).getName().equals(s)) {
                return states.get(i);
            }
        }
        return null;
    }

    public StateMachine integer(String string) {
        if (!variables.containsKey(string)) {
            variables.put(string, 0);
        }
        return this;
    }

    public StateMachine set(String string, int i) {
        getLatestTransition().setSetOperation(true);
        getLatestTransition().setOperationVar(string);
        getLatestTransition().setSetValue(i);
        return this;
    }

    public StateMachine increment(String string) {
        getLatestTransition().setIncrementOperation(true);
        getLatestTransition().setOperationVar(string);
        getLatestTransition().sethasOperation(true);
        return this;
    }

    public StateMachine decrement(String string) {

        getLatestTransition().setDecrementOperation(true);
        getLatestTransition().setOperationVar(string);
        getLatestTransition().sethasOperation(true);
        return this;
    }

    public StateMachine ifEquals(String string, int i) {
        getLatestTransition().setIsConditional(true);
        getLatestTransition().setConditionVariableName(string);
        getLatestTransition().setConditionComparedValue(i);
        getLatestTransition().setIsConditionEqual(true);
        return this;
    }

    public StateMachine ifGreaterThan(String string, int i) {
        getLatestTransition().setIsConditional(true);
        getLatestTransition().setConditionVariableName(string);
        getLatestTransition().setConditionComparedValue(i);
        getLatestTransition().setIsConditionGreater(true);
        return this;
    }

    public StateMachine ifLessThan(String string, int i) {
        getLatestTransition().setIsConditional(true);
        getLatestTransition().setConditionVariableName(string);
        getLatestTransition().setConditionComparedValue(i);
        getLatestTransition().setIsConditionLesser(true);
        return this;
    }

    private Transition getLatestTransition() {
        int size = states.size() - 1;
        int t_size = states.get(size).getTransitions().size() - 1;
        return states.get(size).getTransitions().get(t_size);
    }

}
