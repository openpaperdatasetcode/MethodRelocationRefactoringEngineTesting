package source.pkg;
import java.util.ArrayList;import java.util.List;import target.pkg.TargetClass;
public class SourceClass<T> {private TargetClass<String, Integer> targetInstance = new TargetClass<>();
public static class StaticNestedClass {public void nestedMethod() {}}
public void outerMethod() {class LocalInnerClass {public void localMethod() {}}LocalInnerClass local = new LocalInnerClass();local.localMethod();}
private List<String> methodToMove() {List<String> result = new ArrayList<>();if (targetInstance.targetField > 2) {synchronized (this) {int value = 3;SourceClass<?> sourceInstance = new SourceClass<>();result.add(String.valueOf(sourceInstance.getBaseValue(value)));result.add(targetInstance.targetInnerClass.instanceMethod("arg"));}} else {Runnable runnable = () -> {try {targetInstance.targetInnerClass.abstractMethodImpl();} catch (Exception e) {e.printStackTrace();}};runnable.run();}return result;}
private int getBaseValue(int num) {return num * 2;}}
package target.pkg;
import java.util.List;
private class TargetClass<K, V> extends ParentClass implements Comparable<TargetClass<K, V>> {protected int targetField = 5;protected TargetInnerClass targetInnerClass = new TargetInnerClass();
public class TargetInnerClass {public String instanceMethod(String arg) {return arg;}
public void abstractMethodImpl() {}}
@Overridepublic int compareTo(TargetClass<K, V> o) {return 0;}
// Method will be moved here:// private List<String> methodToMove() { ... }}
class ParentClass {}