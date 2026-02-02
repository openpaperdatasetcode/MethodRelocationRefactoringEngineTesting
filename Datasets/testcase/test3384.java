package test;
import java.lang.reflect.Method;
public class SourceClass {// Two static nested classes (as per source_class feature)static class StaticNested1 {}static class StaticNested2 {}
// Protected instance method (position: source)protected void process(TargetClass target) {labeledBlock: {// Constructor invocationTargetClass newTarget = new TargetClass();
variableCall(target);variableCall(newTarget);
if (target == null) break labeledBlock;
// Used by reflectiontry {Method method = TargetClass.class.getDeclaredMethod("anonymousHelper");method.setAccessible(true);method.invoke(target);} catch (Exception e) {}}
new StaticNested1();new StaticNested2();}
private void variableCall(TargetClass target) {target.doTask();}}
private class TargetClass {// Anonymous inner class (target_feature)private final Runnable anonymousTask = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class executed");}};
public void doTask() {anonymousTask.run();}
private void anonymousHelper() {}}