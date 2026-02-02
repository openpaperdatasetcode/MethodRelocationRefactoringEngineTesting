package test;
enum SourceEnum {INSTANCE;
protected String outerProtected = "source_protected";private int instanceField = 42;
public static class StaticNested {public void nestedMethod(TargetEnum.Inner targetInner) {System.out.println(targetInner.innerField);}}
void process(TargetEnum.Inner targetInner) {// Anonymous inner classRunnable task = new Runnable() {@Overridepublic void run() {variableCall(targetInner);}};task.run();
// Type declaration statementStaticNested staticNested = new StaticNested();
try {// Super constructor invocation (implicit in enum constants)// Access outer protected fieldtargetInner.innerField = outerProtected;
// Access instance fieldtargetInner.counter = this.instanceField;
// Variable call - access target inner's fieldstaticNested.nestedMethod(targetInner);} catch (Exception e) {// no new exception}}
private void variableCall(TargetEnum.Inner targetInner) {System.out.println(targetInner.innerField);}}
protected enum TargetEnum {VALUE1, VALUE2;
public class Inner {public String innerField;public int counter;
public Inner() {super(); // Super constructor invocation}}}