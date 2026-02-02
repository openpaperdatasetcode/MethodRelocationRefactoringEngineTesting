package test;
import java.util.function.Consumer;
// Interface for source record to implementinterface Processable {void process();}
// Source record: default modifier, implements interface, with anonymous inner & static nested classesrecord SourceRecord(String id) implements Processable {// Static nested class (source feature)public static class SourceStaticNested {protected int nestedField;
public SourceStaticNested(int nestedField) {this.nestedField = nestedField;}
public void nestedMethod() {}}
// Anonymous inner class (source feature) - initialized in static blockstatic {Processable anonProcessable = new Processable() {@Overridepublic void process() {new SourceStaticNested(1).nestedMethod();}};anonProcessable.process();}
// Member inner class (for source_inner method position)public class SourceInner {// Overloading method 1 (method type: overloading)protected void overloadedMethod(TargetRecord target) {handleTarget(target);}
// Overloading method 2 (method type: overloading)protected void overloadedMethod(TargetRecord target, int param) {handleTarget(target);System.out.println("Param: " + param);}
private void handleTarget(TargetRecord target) {// NullPointerException (method feature)if (target == null) {throw new NullPointerException("TargetRecord cannot be null");}
// SuperConstructorInvocation with target_feature (this.field & 1) (method feature)class SubNested extends SourceStaticNested {SubNested() {super(1); // Super constructor invocationif (this.nestedField == 1) { // this.field (SubNested's nestedField) & 1variableCall(); // Variable call (method feature)}}}new SubNested();
// Type declaration statement (method feature)TargetRecord.TargetStaticNested targetNested = target.new TargetStaticNested();// Depends_on_inner_class (method feature) - use target's inner classtargetNested.targetInnerMethod();
// Switch case (method feature)switch (target.id().length()) {case 1:// call_method: inner_class type, pos: object initializationConsumer<TargetRecord.TargetStaticNested> consumer = targetNested::targetInnerMethod;consumer.accept(targetNested);break;default:break;}
// requires_try_catch (method feature)try {int parsed = Integer.parseInt(target.id());} catch (NumberFormatException e) {// Empty catch block for required try-catch structure}}
// Variable call target methodprivate void variableCall() {}}
// Implement method from Processable interface@Overridepublic void process() {SourceInner inner = new SourceInner();TargetRecord target = new TargetRecord("1");inner.overloadedMethod(target); // Contains target parameter (per_condition)}}
// Target record: default modifier, with static nested class (target feature)record TargetRecord(String id) {// Static nested class (target feature)public class TargetStaticNested {public void targetInnerMethod() {}}}