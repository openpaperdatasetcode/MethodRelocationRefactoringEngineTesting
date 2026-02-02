package test;
protected class SourceClass {// Member inner classpublic class SourceMemberInner {public record SourceInnerRec(int code) {// Overloading method 1 (method types parameter is:none)protected Object overloadedMethod(TargetClass target) {// Type declaration statementTargetClass.InnerRec rec = target.new InnerRec(1, "data1");Object result = null;
// 2 abstract SwitchExpressionsint switch1 = switch (rec.id()) {case 1 -> 100;case 2 -> 200;default -> 0;};
String switch2 = switch (rec.value()) {case "data1" -> "Processed1";case "data2" -> "Processed2";default -> "Default";};
// Variable callresult = switch1 + "_" + switch2;
// Access instance methodresult += "_" + target.processRec(rec);
// 2 private ConstructorInvocations with obj.fieldTargetClass.Helper helper1 = new TargetClass.Helper(rec.id());TargetClass.Helper helper2 = new TargetClass.Helper(rec.id() * 2);result += "_" + (helper1.getValue() + helper2.getValue());
return result;}
// Overloading method 2 (method types parameter is:none)protected Object overloadedMethod(TargetClass target, int multiplier) {Object base = overloadedMethod(target);// Super constructor invocation in anonymous subclassTargetClass subTarget = new TargetClass() {{super();}};return base + "_multiplied:" + (multiplier * subTarget.baseField);}}}
// Local inner classpublic void useLocalInner() {class LocalProcessor {void execute() {TargetClass target = new TargetClass() {};new SourceMemberInner.SourceInnerRec(2).overloadedMethod(target);}}new LocalProcessor().execute();}}
abstract class TargetClass {protected int baseField = 5;
public TargetClass() {// Anonymous inner classRunnable init = new Runnable() {@Overridepublic void run() {baseField *= 2;}};init.run();}
public record InnerRec(int id, String value) {}
public String processRec(InnerRec rec) {return rec.id() + ":" + rec.value();}
// Private helper class for ConstructorInvocationprivate class Helper {private int value;
private Helper(int value) {this.value = value;}
public int getValue() {return value;}}}