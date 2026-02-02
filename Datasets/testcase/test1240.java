package test.pkg;
import java.util.Arrays;import java.util.List;
abstract class SourceClass {private String sourceField = "source";
public static class StaticNestedClass {public void nestedMethod() {}}
public class SourceInnerClass {public TargetClass methodToMove(TargetClass targetParam) {synchronized (targetParam) {List<String> items = Arrays.asList("a", "b", "c");for (String item : items) {if (targetParam.targetField.length() == 3) {StaticNestedClass.nestedMethod();Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println(SourceClass.this.sourceField);}};runnable.run();targetParam.process(item);}}return targetParam;}}
public TargetClass methodToMove(TargetClass targetParam, int count) {synchronized (targetParam) {List<Integer> nums = Arrays.asList(1, 2, 3);for (int num : nums) {if (targetParam.targetField.length() == 3 && num <= count) {StaticNestedClass.nestedMethod();targetParam.process(String.valueOf(num));}}return targetParam;}}}}
final class TargetClass extends ParentClass {protected String targetField = "abc";
public void process(String data) {}
public void outerMethod() {class LocalInnerClass {public void innerMethod() {}}LocalInnerClass local = new LocalInnerClass();local.innerMethod();}
// Methods will be moved here:// public TargetClass methodToMove(TargetClass targetParam) { ... }//// public TargetClass methodToMove(TargetClass targetParam, int count) { ... }}
class ParentClass {}