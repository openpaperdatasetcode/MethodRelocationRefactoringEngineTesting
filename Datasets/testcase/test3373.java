package test;
sealed enum SourceEnum extends ParentEnum permits SourceEnum.INSTANCE {INSTANCE;
protected String outerProtectedField = "protected_value";
// Instance code block (method_feature position){TargetClass target = new TargetClass();target.instanceMethod1(target);target.instanceMethod2(target);}
class InnerClass {class InnerRec {// Overriding method (no type parameters)@Overridepublic Object process(TargetClass target) {// Constructor invocationTargetClass newTarget = new TargetClass();// Type declaration statementclass LocalType {}new LocalType();
// Expression statementtarget.field = "processed";// Access outer protectedSystem.out.println(SourceEnum.this.outerProtectedField);
variableCall(target);// Anonymous inner classRunnable anonymousTask = new Runnable() {@Overridepublic void run() {target.instanceMethod1(target);}};anonymousTask.run();
return newTarget;}
private void variableCall(TargetClass target) {// instanceReference.methodName(arguments)target.instanceMethod1(target);target.instanceMethod2(target);}}}}
abstract class ParentEnum {public abstract Object process(TargetClass target);}
public enum TargetClass {INSTANCE;
public String field = "initial";
// Target's anonymous inner classprivate final Runnable targetAnonymous = new Runnable() {@Overridepublic void run() {field = "updated_by_anonymous";}};
public void instanceMethod1(TargetClass target) {targetAnonymous.run();}
public void instanceMethod2(TargetClass target) {field = "updated_by_method2";}
@Overridepublic Object process(TargetClass target) {return target;}}