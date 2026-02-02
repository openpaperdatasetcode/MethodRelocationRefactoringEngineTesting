package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
class ParentTarget {}
private class SourceClass {static class StaticNested {}
public void outerMethod() {class LocalInner {class InnerRec {protected List<String> moveMethod(TargetClass... targets) {List<String> result = new ArrayList<>();for (TargetClass target : targets) {TargetClass newTarget = new TargetClass();variableCall(target);
try {Method method = TargetClass.class.getMethod("doTask");method.invoke(target);} catch (Exception e) {}
result.add(callMethod(target));}return result;}
private void variableCall(TargetClass target) {target.doTask();}
protected String callMethod(TargetClass target) {return target.superMethod() + "callResult";}}}new LocalInner().new InnerRec().moveMethod(new TargetClass());}}
final class TargetClass extends ParentTarget {public void doTask() {}
protected String superMethod() {return super.toString();}
protected List<String> moveMethod(TargetClass... targets) {return new ArrayList<>();}}