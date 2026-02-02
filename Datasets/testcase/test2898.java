package sourcepkg;
import targetpkg.TargetClass;import java.util.ArrayList;import java.util.List;
public class SourceClass {private TargetClass<String> targetField; // Per condition: source contains target's field
public static class StaticNestedSource {}public class MemberInnerSource {}
// Overloading method 1protected List<String> process() {return process(targetField);}
// Overloading method 2 (to be refactored)protected List<String> process(TargetClass<String> target) {List<String> result = new ArrayList<>();
// Constructor invocation: target static nested classTargetClass.StaticNestedTarget nested = new TargetClass.StaticNestedTarget();
// 2 protected NumberLiteral expressionsprotected int num1 = 10;protected long num2 = 20L;
// Expression statementtargetField.setValue("processed");nested.process(num1, num2);
// Variable call: target instance, inner/static nested classesresult.add(target.getValue());result.add(String.valueOf(num1 + num2));new StaticNestedSource();new MemberInnerSource();
return result;}}
package targetpkg;
import java.util.List;
/**
TargetClass - abstract generic class with static nested class
@param <T> Type parameter for generic support*/public abstract class TargetClass<T> {private T value;
/**
Static nested class in target
*/
public static class StaticNestedTarget {
public void process(int a, long b) {}
}
public void setValue(T val) {this.value = val;}
public T getValue() {return value;}
public abstract List<String> abstractMethod();}