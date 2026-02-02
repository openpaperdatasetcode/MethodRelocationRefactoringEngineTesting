package test;
// Parent interface for overriding featureinterface Processable {Object process(TargetClass target); // Interface method (return Object)}
// Parent class for target_class extends featureclass ParentTarget {protected String parentField = "parent_data";}
/**
Abstract target class with extends and anonymous inner class (target_features)*/abstract class TargetClass extends ParentTarget {private String data;
public TargetClass(String data) {this.data = data;}
public abstract void doTask();
public String getData() {return data;}
public void setData(String data) {this.data = data;}
// Anonymous inner class (target_feature)public Runnable getTargetTask() {return new Runnable() {@Overridepublic void run() {data = data + "_target_anonymous";}};}}
/**
Public source class with anonymous inner and member inner classes*/public class SourceClass implements Processable {// Member inner class (source_class feature)class MemberInner {public void processInner(TargetClass target) {target.setData(target.getData() + "_inner_processed");}}
// Anonymous inner class (source_class feature)private final Runnable sourceTask = new Runnable() {@Overridepublic void run() {System.out.println("Source anonymous task executed");}};
/**
Default access overriding method (position: source)
override_violation: Intentionally changes return type compatibility (violates LSP)*/@Overridepublic String process(TargetClass target) { // Return String instead of Object (violation)// Protected Name expression (numbers=3: three named identifiers)protected String name1 = "param1", name2 = "param2", name3 = "param3";String combinedName = name1 + name2 + name3;
// Assert statementassert target != null : "Target cannot be null";assert target.getData().length() > 0 : "Target data cannot be empty";
// If statementif (combinedName.length() == 9) {target.setData(target.getData() + "_" + combinedName);}
// Variable callvariableCall(target);new MemberInner().processInner(target);sourceTask.run();target.getTargetTask().run();
return target.getData();}
private void variableCall(TargetClass target) {target.doTask();}}
/**
Concrete implementation of abstract TargetClass*/class TargetImpl extends TargetClass {public TargetImpl(String data) {super(data);}
@Overridepublic void doTask() {}}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) {
TargetClass target = new TargetImpl("test");
SourceClass source = new SourceClass();
Object result = source.process(target);
assert result.equals("test_param1param2param3_inner_processed_target_anonymous") : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}