package test.refactor.movemethod;
import java.util.ArrayList;import java.util.List;
/**
TargetInterface Javadoc
Implements Iterable to satisfy target_feature and contains anonymous inner class*/public interface TargetInterface extends Iterable<String> {String TARGET_FIELD = "targetValue";
@Overridedefault java.util.Iterator<String> iterator() {return new java.util.Iterator<>() {private int index = 0;private final List<String> data = List.of("a", "b", "c");
@Overridepublic boolean hasNext() {return index < data.size();}
@Overridepublic String next() {return data.get(index++);}};}}
interface SourceInterface permits SourceImpl {static class StaticNestedClass1 {public String getValue() {return "nested1";}}
static class StaticNestedClass2 {public String getValue() {return "nested2";}}
protected List<String> processInstanceMethod(TargetInterface targetParam);}
class SourceImpl implements SourceInterface {private String instanceField = "sourceInstanceField";
@Overridepublic List<String> processInstanceMethod(TargetInterface targetParam) {List<String> result = new ArrayList<>();
// Type declaration statementSourceInterface.StaticNestedClass1 nested1 = new SourceInterface.StaticNestedClass1();SourceInterface.StaticNestedClass2 nested2 = new SourceInterface.StaticNestedClass2();
// LabeledStatement (protected, target_feature: obj.field, 1, pos: diff_package_others)protectedLabel: {String fieldVal = targetParam.TARGET_FIELD;if (fieldVal.isEmpty()) break protectedLabel;result.add(fieldVal);}
// Recursion (default, method_feature: 1, source, recursion, new ClassName().method(), pos: do-while)int count = 0;do {if (count < 1) {new SourceImpl().recursiveMethod(count);}count++;} while (count <= 1);
// Variable call & access_instance_fieldresult.add(instanceField);result.add(nested1.getValue());result.add(nested2.getValue());
// Use target parameter (per_condition)for (String s : targetParam) {result.add(s);}
return result;}
default void recursiveMethod(int depth) {if (depth >= 1) return;new SourceImpl().recursiveMethod(depth + 1); // Recursion call}}
// Test case classpublic class MoveMethodTest5153 {public static void main(String[] args) {SourceInterface source = new SourceImpl();TargetInterface target = new TargetInterface() {};List<String> result = source.processInstanceMethod(target);
assert result.contains(TargetInterface.TARGET_FIELD) : "Target field check";assert result.contains("sourceInstanceField") : "Source instance field check";assert result.contains("nested1") && result.contains("nested2") : "Static nested class check";assert result.size() == 6 : "Result list size check";}}