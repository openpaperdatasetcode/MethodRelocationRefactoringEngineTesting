package samepkg;
public class SourceClass {public class MemberInnerSource {}
protected TargetClass processVarargs(TargetClass targetParam, String... args) {// Type declaration statementClass<? extends TargetClass> targetType = targetParam.getClass();
// VariableDeclarationStatement (target static fields, 2 fields)private int field1 = TargetClass.STATIC_FIELD1;private int field2 = TargetClass.STATIC_FIELD2;
// Enhanced for statementfor (String arg : args) {targetParam.memberInnerTarget.process(arg);}
// Anonymous inner classRunnable anonymous = new Runnable() {@Overridepublic void run() {targetParam.doSomething();}};anonymous.run();
// Private instance method in if/else conditions (calls parent class instance method)if (args.length > 0) {callParentInstanceMethod(targetParam.getParentInstance());} else {callParentInstanceMethod(new ParentClass());}
// Variable calltargetParam.setValue(field1 + field2);return targetParam;}
private void callParentInstanceMethod(ParentClass parentInstance) {parentInstance.parentInstanceMethod("arg1");}}
package samepkg;
public class TargetClass extends ParentClass {public static int STATIC_FIELD1 = 10;public static int STATIC_FIELD2 = 20;private int value;
public class MemberInnerTarget {public void process(String arg) {}}
public MemberInnerTarget memberInnerTarget = new MemberInnerTarget();
public void doSomething() {}
public void setValue(int val) {this.value = val;}
public ParentClass getParentInstance() {return this;}
default Object recursiveLambda(int n) {// Recursion + lambda expressionFunction<Integer, Object> recursiveFunc = (num) -> {if (num <= 1) return num;return recursiveFunc.apply(num - 1);};return recursiveFunc.apply(n);}}
package samepkg;
public class ParentClass {public void parentInstanceMethod(String arg) {}}
import java.util.function.Function;