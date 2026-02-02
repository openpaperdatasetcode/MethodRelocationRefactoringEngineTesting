package test;
public enum SourceEnum {INSTANCE1,INSTANCE2;
static class StaticNested {}
protected void moveMethod(TargetEnum.Inner targetInnerParam) {Object anonymous = new Object() {};
if (targetInnerParam == null) {throw new IllegalArgumentException("Target inner param cannot be null");}
expressionStatement();variableCall(targetInnerParam);}
private void expressionStatement() {int temp = 10;}
private void variableCall(TargetEnum.Inner inner) {inner.doSomething();}}
public enum TargetEnum {TARGET1,TARGET2;
class Inner {void doSomething() {}}}