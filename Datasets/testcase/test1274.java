package test.refactoring.movemethod;
import java.util.List;import java.util.ArrayList;import java.util.function.Function;
interface DataProcessor {List<String> process(String input);List<String> process(String input, int limit);}
private class SourceClass implements DataProcessor {private TargetClass targetField;private String outerPrivateField = "source_private";
@Overridepublic List<String> process(String input) {List<String> result = new ArrayList<>();if (targetField == null) {return result;}
try {TargetClass.StaticNestedTarget nested1 = new TargetClass.StaticNestedTarget(this.outerPrivateField, input);TargetClass.StaticNestedTarget nested2 = new TargetClass.StaticNestedTarget(this.outerPrivateField, input.toUpperCase());
result.addAll(nested1.processData());result.addAll(nested2.processData());
targetField.setProcessor(str -> this.process(str, 3));return this;} catch (IllegalArgumentException e) {return new ArrayList<>();}}
@Overridepublic List<String> process(String input, int limit) {List<String> result = new ArrayList<>();for (int i = 0; i < limit; i++) {result.add(input + "" + outerPrivateField + "" + i);}return result;}
public void setTargetField(TargetClass targetField) {this.targetField = targetField;}}
private class TargetClass {private Function<String, List<String>> processor;
public static class StaticNestedTarget {private String field1;private String field2;
public StaticNestedTarget(String field1, String field2) {this.field1 = field1;this.field2 = field2;}
public List<String> processData() {List<String> data = new ArrayList<>();data.add(field1 + "-" + field2);return data;}}
protected TargetClass processVarargs(String... inputs) {for (String input : inputs) {if (processor != null) {processor.apply(input);}}return this;}
public void setProcessor(Function<String, List<String>> processor) {this.processor = processor;}}
public class MethodCaller {public static void main(String[] args) {SourceClass source = new SourceClass();TargetClass target = new TargetClass();source.setTargetField(target);
// Call overriding methodList<String> result1 = source.process("test");System.out.println(result1);
// Call overloaded methodList<String> result2 = source.process("test", 2);System.out.println(result2);
// Call inner class varargs method with method referenceTargetClass.StaticNestedTarget nested = new TargetClass.StaticNestedTarget("caller", "data");target.setProcessor(nested::processData);TargetClass processedTarget = target.processVarargs("arg1", "arg2", "arg3");}}