package main.metamodel;

public class Transition {

    private Object event;
    private State target;
    private String placeholder;
    private boolean hasSetOperation = false;
    private boolean hasDecrement = false;
    private boolean hasIncrement = false;
    private boolean isConditional = false;
    private boolean isEqual = false;
    private boolean isGreater = false;
    private boolean isLesser = false;
    private boolean hasOps = false;
    private String operationVar = null;
    private String conditionalVar = null;
    private Integer comparedVal = 0;
    private Integer setVal = 0;

    public Transition(Object event, String placeholder) {

        this.event = event;
        this.placeholder = placeholder;

    }

    public Object getEvent() {
        return event;
    }

    public void setSetValue(Integer val) {
        setVal = val;
    }

    public Integer getSetValue() {
        return setVal;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String s) {
        placeholder = s;
    }

    public void setTarget(State t) {
        target = t;
    }

    public State getTarget() {
        return target;
    }

    public void setSetOperation(boolean b) {
        hasSetOperation = b;
    }

    public boolean hasSetOperation() {
        return hasSetOperation;
    }

    public void setIncrementOperation(boolean b) {
        hasIncrement = b;
    }

    public boolean hasIncrementOperation() {
        return hasIncrement;
    }

    public void setDecrementOperation(boolean b) {
        hasDecrement = b;
    }

    public boolean hasDecrementOperation() {
        return hasDecrement;
    }

    public void setOperationVar(String s) {
        operationVar = s;
    }

    public Object getOperationVariableName() {
        return operationVar;
    }

    public void setIsConditional(boolean b) {
        isConditional = b;
    }

    public boolean isConditional() {
        return isConditional;
    }

    public void setConditionVariableName(String s) {
        conditionalVar = s;
    }

    public Object getConditionVariableName() {
        return conditionalVar;
    }

    public void setConditionComparedValue(int i) {
        comparedVal = i;
    }

    public Integer getConditionComparedValue() {
        return comparedVal;
    }

    public void setIsConditionEqual(boolean b) {
        isEqual = b;
    }

    public boolean isConditionEqual() {
        return isEqual;
    }

    public void setIsConditionGreater(boolean b) {
        isGreater = b;
    }

    public boolean isConditionGreaterThan() {
        return isGreater;
    }

    public void setIsConditionLesser(boolean b) {
        isLesser = b;
    }

    public boolean isConditionLessThan() {
        return isLesser;
    }

    public void sethasOperation(boolean b) {
        hasOps = b;
    }

    public boolean hasOperation() {
        return hasOps;
    }

}
