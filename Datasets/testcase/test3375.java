package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
protected class SourceClass {static class StaticNested {}
class InnerClass {class InnerRec {// Varargs method (no type parameters)protected List<String> process(TargetClass... targets) throws Exception {List<String> result = new ArrayList<>();super(); // Super constructor invocation
for (TargetClass target : targets) {new TargetClass(); // Constructor invocation
// Private SynchronizedStatement (target_feature: this.field=3)private Object lock = new Object();synchronized (lock) {if (target.this.field != 3) {throw new IllegalArgumentException("Field value mismatch");}variableCall(target);result.add(String.valueOf(target.field));}
// Try statement + used_by_reflectiontry {Method method = TargetClass.class.getDeclaredMethod("anonymousInnerTask");method.setAccessible(true);method.invoke(target);} catch (Exception e) {throw new Exception("Reflection failed", e);}
// Local inner classclass LocalInner {void addToResult() {result.add("processed:" + target.field);}}new LocalInner().addToResult();new StaticNested();}
return result;}
private void variableCall(TargetClass target) {target.publicMethod();}}}}
public class TargetClass {public int field = 3; // this.field=3
public TargetClass() {// Target's anonymous inner classRunnable anonymousTask = new Runnable() {@Overridepublic void run() {field = 3; // Ensure field value matches target_feature}};anonymousTask.run();}
public void publicMethod() {}
private void anonymousInnerTask() {}
protected List<String> process(TargetClass... targets) {List<String> result = new ArrayList<>();for (TargetClass t : targets) result.add(String.valueOf(t.field));return result;}}