package test.refactoring;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.lang.reflect.Method;

abstract class AbstractSource {
public abstract void abstractMethod1();
public abstract void abstractMethod2();
public abstract void abstractMethod3();
}

public class SourceClass extends AbstractSource {
private String sourceField = "source_data";

class MemberInnerClass {
public String getInnerData() {
return sourceField + "_inner";
}
}

strictfp class CallMethodInner {
public static String processCollection(Collection<TargetClass.TargetInner> targets) {
StringBuilder sb = new StringBuilder();
targets.forEach(target -> sb.append(SourceClass.MemberInnerClass.class.getSimpleName())
.append(": ").append(target.getTargetData()));
return sb.toString();
}
}

@Override
public void abstractMethod1() {
System.out.println("Abstract method 1 implemented");
}

@Override
public void abstractMethod2() {
System.out.println("Abstract method 2 implemented");
}

@Override
public void abstractMethod3() {
System.out.println("Abstract method 3 implemented");
}

protected Object recursiveMethod(int depth, TargetClass.TargetInner targetParam) {
class LocalInnerClass {
String handleTarget(TargetClass.TargetInner target) {
return target.getTargetData() + "_processed";
}
}

LocalInnerClass localHandler = new LocalInnerClass();
assert targetParam != null : "Target parameter cannot be null";
assert depth >= 0 : "Depth cannot be negative";

TargetClass.TargetInner instance1 = new TargetClass.TargetInner("instance_1");
TargetClass.TargetInner instance2 = new TargetClass.TargetInner("instance_2");

depth == 0 ? this.abstractMethod1() : (depth == 1 ? this.abstractMethod2() : this.abstractMethod3());

try {
Method reflectMethod = TargetClass.TargetInner.class.getMethod("getTargetData");
String reflectResult = (String) reflectMethod.invoke(targetParam);
variableCall(reflectResult);
} catch (Exception e) {
e.printStackTrace();
}

List<TargetClass.TargetInner> targets = new ArrayList<>();
targets.add(targetParam);
targets.add(instance1);
targets.add(instance2);
CallMethodInner.processCollection(targets);

return depth == 0 ? localHandler.handleTarget(targetParam) : recursiveMethod(depth - 1, targetParam);
}

private void variableCall(String data) {
System.out.println("Variable call: " + data);
}
}

final class TargetClass {
static class TargetInner {
private String targetData;

public TargetInner(String targetData) {
this.targetData = targetData;
}

public String getTargetData() {
return targetData;
}
}
}