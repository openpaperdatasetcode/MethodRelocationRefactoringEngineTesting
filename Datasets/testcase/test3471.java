package test;
// Protected source class (same package) with member inner and anonymous inner classesprotected class SourceClass {// Member inner class (source_class feature)abstract class InnerHelper {// Abstract SwitchExpression (numbers=1, modifier=abstract)abstract int evaluateSwitch(TargetClass target);}
// Anonymous inner class (source_class feature)private final Runnable anonymousTask = new Runnable() {@Overridepublic void run() {System.out.println("Source anonymous task executed");}};
// Final instance method (position: source)public final void process(TargetClass target) {// Super constructor invocation (in inner class instance)InnerHelper helper = new InnerHelper() {@Overrideint evaluateSwitch(TargetClass t) {// SwitchExpression (numbers=1)return switch (t.getType()) {case 1 -> 10;case 2 -> 20;default -> throw new IllegalArgumentException("Invalid type");};}};
// Expression statementtarget.setData("processed");
// Access instance methodint switchResult = helper.evaluateSwitch(target);
// Variable callvariableCall(target);
// Break statementfor (int i = 0; i < 3; i++) {if (i == switchResult / 10) {break;}target.updateCount(i);}
anonymousTask.run();TargetClass.StaticNested.assist(target);}
private void variableCall(TargetClass target) {target.doTask();}}
// Final target class with static nested class (target_feature)final class TargetClass {private String data;private int type;private int count;
// Static nested class (target_feature)public static class StaticNested {public static void assist(TargetClass target) {}}
public void doTask() {}
public void setData(String data) {this.data = data;}
public int getType() {return type;}
public void setType(int type) {this.type = type;}
public void updateCount(int increment) {this.count += increment;}
public int getCount() {return count;}}
// Test class for Move Method refactoring verificationpublic class RefactoringTest {public static void main(String[] args) {TargetClass target = new TargetClass();target.setType(1);SourceClass source = new SourceClass();source.process(target);assert target.getCount() == 0 : "Refactoring test failed";System.out.println("Refactoring test passed");}}