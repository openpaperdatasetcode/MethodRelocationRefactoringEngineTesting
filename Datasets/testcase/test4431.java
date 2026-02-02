package test;
import java.util.List;import java.util.ArrayList;
interface DataProcessor {List<String> processData();}
abstract class SourceClass implements DataProcessor {static class StaticNestedA {}static class StaticNestedB {}
@Overridepublic final List<String> processData() {TargetClass<String> target = new TargetClass<>();TargetClass.Nested targetNested = new TargetClass.Nested();List<String> result = new ArrayList<>();int flag = 1;
try {String fieldVal = target.targetField;result.add(fieldVal);
switch (flag) {case 1:result.add(targetNested.nestedField);break;default:result.add("default");}
String callResult = callMethod(target);result.add(callResult);} finally {System.out.println("Process completed");}
return result;}
private final String callMethod(TargetClass<?> param) {String result = "";for (int i = 0; i < 3; i++) {TargetClass.Nested nested = new TargetClass.Nested();result = super.toString() + nested.nestedField;}return result;}}
class TargetClass<T> {String targetField = "target-data";static class Nested {String nestedField = "nested-data";}}