package test.refactoring.movemethod;
import java.util.List;import java.util.ArrayList;import java.util.function.Predicate;
private class SourceClass extends SuperSourceClass {protected List<String> filterData(TargetClass targetParam, Predicate<String> filter) {List<String> result = new ArrayList<>();if (targetParam == null) {return result;}
labeledLoop:for (String item : TargetClass.StaticNestedTarget.DATA_LIST) {try {if (!filter.test(item)) {break labeledLoop;}result.add(super.formatItem(item) + "_" + TargetClass.STATIC_FIELD);} catch (Exception e) {continue labeledLoop;}}
return result;}}
class SuperSourceClass {protected String formatItem(String item) {return "formatted_" + item;}}
strictfp class TargetClass {public static final String STATIC_FIELD = "target_static";
public static class StaticNestedTarget<T extends CharSequence> {public static final List<String> DATA_LIST = List.of("item1", "item2", "item3");
public T process(T data) {return data;}}}
public class MoveMethodTest5177 {public static void main(String[] args) {SourceClass source = new SourceClass();TargetClass target = new TargetClass();
List<String> filtered = source.filterData(target, s -> s.startsWith("item"));System.out.println(filtered);}}