package main;

import main.metamodel.Machine;
import java.util.*;
import main.metamodel.State;
import main.metamodel.Transition;

public class MachineInterpreter {
    private Machine m;
    private State current = null;

    public void run(Machine m) {
        this.m = m;
        current = m.getInitialState();
    }

    public State getCurrentState() {
        return current;
    }

    public void processEvent(String string) {
        if (current == null)
            return;
        for (Transition transition : current.getTransitions()) {
            if (transition.getEvent().equals(string)) {
                try {
                    if (transition.isConditional()) {
                        String varName = (String) transition.getConditionVariableName();
                        int comparedValue = transition.getConditionComparedValue();
                        int varValue = getInteger(varName);

                        boolean conditionMet = false;
                        if (transition.isConditionEqual()) {
                            conditionMet = (varValue == comparedValue);
                        } else if (transition.isConditionGreaterThan()) {
                            conditionMet = (varValue > comparedValue);
                        } else if (transition.isConditionLessThan()) {
                            conditionMet = (varValue < comparedValue);
                        }

                        if (!conditionMet) {
                            continue; 
                        }
                    }
                } catch (Exception e) {
                    continue; 
                }
                try {
                    if (transition != null) {
                        if (transition.hasIncrementOperation()) {
                            String varName = (String) transition.getOperationVariableName();
                            if (m.getVars().containsKey(varName)) {
                                m.getVars().put(varName, m.getVars().get(varName) + 1);
                            }
                        } else if (transition.hasDecrementOperation()) {
                            String varName = (String) transition.getOperationVariableName();
                            if (m.getVars().containsKey(varName)) {
                                m.getVars().put(varName, m.getVars().get(varName) - 1);
                            }
                        } else if (transition.hasSetOperation()) {
                            String varName = (String) transition.getOperationVariableName();
                            if (m.getVars().containsKey(varName)) {
                                m.getVars().put(varName, transition.getSetValue());
                            }
                        }
                        current = transition.getTarget();
                        return; 
                    }
                } catch (Exception e) {
                    continue; 
                }
            }
        }
    }

    public int getInteger(String string) {
        return m.getVars().get(string);
    }

}
