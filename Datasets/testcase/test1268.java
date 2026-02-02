package test.refactoring.movemethod;
import java.util.List;import java.util.ArrayList;import java.util.Iterator;
sealed class SourceClass permits SourceSubClass {protected TargetClass targetField;protected String outerProtectedField = "protected_data";
public List<String> collectData(String prefix) {List<String> result = new ArrayList<>();if (targetField == null) {return result;}
Iterator<String> iterator = targetField.getItems().iterator();while (iterator.hasNext()) {String item = iterator.next();for (int i = 0; i < 2; i++) {Object nestedResult = getNestedData(item, prefix + i);result.add(nestedResult.toString());}}
class LocalInnerClass {void useOuterThis() {result.add(SourceClass.this.outerProtectedField);}}new LocalInnerClass().useOuterThis();
return result;}
private Object getNestedData(String item, String suffix) {OtherClass other = new OtherClass();return other.getFirst().getSecond().getThird(item + suffix);}
class MemberInnerClass {void accessTargetField() {targetField.execute(outerProtectedField);}}}
non-sealed class SourceSubClass extends SourceClass {}
strictfp class TargetClass implements Iterable<String> {private List<String> items = new ArrayList<>();
public TargetClass() {items.add("target_item1");items.add("target_item2");}
public void execute(String data) {Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println("Executing with: " + data);}};runnable.run();}
@Overridepublic Iterator<String> iterator() {return items.iterator();}
public List<String> getItems() {return items;}}
class OtherClass {public MiddleClass getFirst() {return new MiddleClass();}
public static class MiddleClass {public FinalClass getSecond() {return new FinalClass();}}
public static class FinalClass {public Object getThird(String input) {return "nested_" + input;}}}
// Test classpublic class MoveMethodTest5165 {public static void main(String[] args) {SourceClass source = new SourceClass();source.targetField = new TargetClass();source.new MemberInnerClass().accessTargetField();
List<String> data = source.collectData("prefix_");System.out.println(data);}}