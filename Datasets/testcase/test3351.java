package test;
public class SourceClass {static class StaticNested {}
class MemberInner {// Synchronized instance method (method_feature)public synchronized void innerMethod(TargetClass target) {target.doTask();}}
// Static method with type parameter:keywordspublic static TargetClass process(String ifKeyword, String forKeyword) {TargetClass target = new TargetClass(() -> new MemberInner().innerMethod(target)); // Parameter list of constructor position
// For statementfor (int i = 0; i < 1; i++) {// Expression statementtarget.field = i;variableCall(target);}
new StaticNested();new MemberInner();return target;}
private static void variableCall(TargetClass target) {target.doTask();// Method reference: ClassName::methodNameRunnable task = TargetClass::doStaticTask;task.run();}
// Override violation (Object.equals() return type mismatch)@Overridepublic TargetClass equals(Object obj) {return obj instanceof SourceClass ? new TargetClass(null) : null;}}
// Target class with empty modifierclass TargetClass {public int field = 0;
public TargetClass(Runnable initTask) {if (initTask != null) initTask.run();}
public void doTask() {}
public static void doStaticTask() {}
public static TargetClass process(String ifKeyword, String forKeyword) {return new TargetClass(null);}}