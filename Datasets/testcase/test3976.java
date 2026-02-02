package test;
import java.util.List;import java.util.ArrayList;
strictfp class TargetClass {static class TargetNested {int nestedField;
TargetNested(int val) {this.nestedField = val;}
List<String> nestedMethod() {return new ArrayList<>();}
void recursiveInnerMethod(int depth) {if (depth <= 0) return;recursiveInnerMethod(depth - 1);}}}
public class SourceClass {private TargetClass.TargetNested targetInnerField;private int value = 5;
static class SourceStaticNested {}
class SourceMemberInner {List<String> innerMethod() {return new ArrayList<>();}}
private List<String> instanceMethod() {return new ArrayList<>();}
public synchronized SourceClass() {targetInnerField = new TargetClass.TargetNested(3);
assert targetInnerField.nestedField == 3;
List<String>[] arr = new List[]{instanceMethod(),new SourceClass().new SourceMemberInner().innerMethod(),targetInnerField.nestedMethod()};
Runnable lambda = () -> System.out.println(this.value);lambda.run();
targetInnerField.recursiveInnerMethod(3);}}