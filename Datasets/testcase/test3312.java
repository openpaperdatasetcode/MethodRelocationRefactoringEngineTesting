package test;
interface TargetInterface {}
class TargetParent {protected int superField = 1;}
non-sealed record SourceRecord(String data) {static class StaticNested {}
class MemberInner {class InnerRec {public final <T extends TargetParent & TargetInterface> int getValue(TargetRecord<T> target) throws Exception {super(); // Super constructor invocationaccess_instance_field(target);
labeledBlock: {if (target.super.superField != 1) break labeledBlock;variableCall(target);}
// Three volatile SuperFieldAccessvolatile int sf1 = target.super.superField;volatile int sf2 = target.super.superField;volatile int sf3 = target.super.superField;
switch (sf1) {case 1:target.innerClass.doTask();break;default:throw new Exception("Invalid super field value");}
return sf1;}
private <T extends TargetParent & TargetInterface> void variableCall(TargetRecord<T> target) {target.staticNested.doTask();}
private <T extends TargetParent & TargetInterface> void access_instance_field(TargetRecord<T> target) {System.out.println(target.super.superField);}}}}
non-sealed record TargetRecord<T extends TargetParent & TargetInterface>(T value) implements TargetInterface {static class StaticNested {public void doTask() {}}
class TargetInner {public void doTask() {}}
private final TargetInner innerClass = new TargetInner();public final StaticNested staticNested = new StaticNested();
{new Runnable() { // Anonymous inner class@Overridepublic void run() {}}.run();}
public final int getValue() {return value.superField;}}