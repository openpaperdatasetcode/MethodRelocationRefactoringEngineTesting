package test;
import java.util.List;import java.util.ArrayList;
public record SourceRecord(int sourceField) {public class InnerOne {}public class InnerTwo {}
int instanceMethod(TargetRecord targetParam) {List<String> list = new ArrayList<>();Runnable r = () -> list.addAll(this.targetMethod(targetParam));r.run();
int sum = 0;for (String s : list) {sum += s.length();}
TargetRecord.Nested nested = new TargetRecord.Nested();sum += nested.getValue();
return sum;}
int instanceMethod(String str) {return str.length();}
public List<String> targetMethod(TargetRecord target) {List<String> result = new ArrayList<>();result.add(String.valueOf(target.targetField()));return result;}}
public record TargetRecord(int targetField) {public static class Nested {int getValue() {return 5;}}}