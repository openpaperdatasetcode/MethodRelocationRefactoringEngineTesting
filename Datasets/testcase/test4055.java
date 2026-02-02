package test;
import java.util.ArrayList;import java.util.List;
public enum SourceEnum implements Runnable {// Source contains target field (matches per_condition)INSTANCE(TargetEnum.VALUE);
private final TargetEnum targetField;
SourceEnum(TargetEnum targetField) {this.targetField = targetField;}
// Overloading method 1 to be refactoredstrictfp Object processTarget() {// Lambda: () -> System.out.println(this.value)Runnable printLambda = () -> System.out.println(this.targetField.value);
// Variable call: access target inner classTargetEnum.TargetInner inner = targetField.new TargetInner();List<String> result = inner.getProcessedList();
// While loop (pos for overriding inner methods)int count = 0;while (count < 3) {result = inner.processStep(result); // 1st overriding inner methodresult = inner.filterStep(result); // 2nd overriding inner methodresult = inner.formatStep(result); // 3rd overriding inner methodcount++;}
printLambda.run();return result;}
// Overloading method 2 (matches "method.type: overloading")strictfp Object processTarget(String suffix) {// Lambda: () -> System.out.println(this.value)Runnable printLambda = () -> System.out.println(this.targetField.value + suffix);
// Variable call: access target inner class with parameterTargetEnum.TargetInner inner = targetField.new TargetInner(suffix);List<String> result = new ArrayList<>();
// While loop (pos for overriding inner methods)int count = 0;while (count < 3) {result = inner.processStep(result); // 1st overriding inner methodresult = inner.filterStep(result); // 2nd overriding inner methodresult = inner.formatStep(result); // 3rd overriding inner methodcount++;}
printLambda.run();return result;}
@Overridepublic void run() {this.processTarget();this.processTarget("_overload");}}
protected enum TargetEnum {VALUE("target_default");
final String value;
TargetEnum(String value) {this.value = value;}
// Member inner class (target_class feature)class TargetInner extends TargetInnerParent {private final String suffix;
// Default constructorTargetInner() {this("");}
// Parameterized constructorTargetInner(String suffix) {this.suffix = suffix;}
// 1st overriding inner method (matches method_feature)@Overrideprotected List<String> processStep(List<String> list) {list.add(TargetEnum.this.value + suffix);return list;}
// 2nd overriding inner method (matches method_feature)@Overrideprotected List<String> filterStep(List<String> list) {list.removeIf(item -> item.isEmpty());return list;}
// 3rd overriding inner method (matches method_feature)@Overrideprotected List<String> formatStep(List<String> list) {return list.stream().map(item -> "[" + item + "]").toList();}
List<String> getProcessedList() {return super.initList(); // super.methodName() (matches method_feature)}}
// Parent class for inner class overridingabstract static class TargetInnerParent {protected List<String> initList() {return new ArrayList<>();}
protected abstract List<String> processStep(List<String> list);
protected abstract List<String> filterStep(List<String> list);
protected abstract List<String> formatStep(List<String> list);}}