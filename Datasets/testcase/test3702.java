import java.util.ArrayList;import java.util.List;import java.util.Objects;
public class SourceClass extends SuperSourceClass {protected TargetClass targetField = new TargetClass();private int instanceField = 100;
public static class StaticNestedClass {public class NestedInner {@Overridepublic int method(TargetClass target) {super();
synchronized (target) {if (target.staticNested.count <= 0) {throw new IllegalArgumentException("Count cannot be negative");}; // Empty statement
TargetClass result = OthersClass::createTarget;variableCall(target);
if (target.staticNested.count == 1) {return instanceField + accessOuterProtected();} else {return target.staticNested.count + recursiveCall(target, target.staticNested.count - 1);}}}
private int recursiveCall(TargetClass target, int depth) {if (depth <= 0) return 0;return depth + recursiveCall(target, depth - 1);}
private void variableCall(TargetClass target) {target.staticNested.updateCount();}
private int accessOuterProtected() {return SourceClass.this.targetField.staticNested.count * 2;}}}
{new Runnable() {@Overridepublic void run() {StaticNestedClass.NestedInner inner = new StaticNestedClass().new NestedInner();inner.method(targetField);}}.run();}}
class SuperSourceClass {public int method(TargetClass target) {return 0;}}
class OthersClass {public static TargetClass createTarget() {return new TargetClass();}}
protected class TargetClass {public StaticNested staticNested = new StaticNested();
public static class StaticNested {public int count = 1;
public void updateCount() {count++;}}
strictfp public List<String> processInCollection(List<Integer> list) {List<String> result = new ArrayList<>();list.forEach(num -> {result.add(String.valueOf(processOverloaded(num)));result.add(String.valueOf(processOverloaded(num, "prefix_")));});return result;}
private int processOverloaded(int num) {return num * staticNested.count;}
private String processOverloaded(int num, String prefix) {return prefix + num;}}