package test;
import com.other.DiffPackageHandler;import java.util.ArrayList;import java.util.List;import java.util.function.Function;
strictfp class SourceClass extends SourceParent {private String outerPrivate = "outer_private_data";
// Anonymous inner classprivate Runnable initializer = new Runnable() {@Overridepublic void run() {processLists(List.of("init1"), List.of("init2"));}};
/**
Varargs method that processes multiple List parameters
Handles target class interactions and various statements
@param lists Varargs of List<String> to process
@return Combined list of results*/strictfp List<String> processLists(List<String>... lists) {List<String> results = new ArrayList<>();
// Super constructor invocation in target subclassTargetClass enhancedTarget = new TargetClass() {{super("enhanced_base");}};
// Expression statementString base = enhancedTarget.baseValue;results.add(base);
// Variable callfor (List<String> list : lists) {results.addAll(TargetClass.StaticNested.processList(list));}
// Access outer privateresults.add(outerPrivate + "_" + enhancedTarget.getDerivedValue());
// Access instance fieldresults.add(String.valueOf(enhancedTarget.counter));
// Super keywordsresults.add("Parent value: " + super.parentValue);
// With boundsclass BoundedProcessor<T extends CharSequence & Comparable<T>> {List<T> process(T... items) {List<T> processed = new ArrayList<>();for (T item : items) {processed.add(item);}return processed;}}results.addAll(new BoundedProcessor<String>().process("bound1", "bound2"));
// For statementfor (int i = 0; i < 3; i++) {enhancedTarget.counter += i;}
// Lambda expressions with inner_class overloading methodsFunction<String, Object> processor1 = s -> TargetClass.StaticNested.transform(s);Function<String, Object> processor2 = s -> TargetClass.StaticNested.transform(s, 2);results.add(processor1.apply("lambda1").toString());results.add(processor2.apply("lambda2").toString());
// Diff package others with ContinueStatement (3 super.field references)DiffPackageHandler.handleContinue(enhancedTarget);
return results;}
// Local inner classpublic void localHandler() {class ListValidator {boolean isValid(List<String> list) {return list != null && !list.isEmpty();}}new ListValidator().isValid(List.of("local"));}}
class SourceParent {protected String parentValue = "parent_base";}
class TargetClass {protected String baseValue;public int counter = 0;
public TargetClass() {this("default_base");}
public TargetClass(String base) {this.baseValue = base;}
public String getDerivedValue() {return baseValue + "_derived";}
// Static nested class (target feature)public static class StaticNested {// Overloading method 1public static List<String> processList(List<String> list) {return new ArrayList<>(list);}
// Overloading method 2public static String transform(String input) {return input.toUpperCase();}
// Overloading method 3public static String transform(String input, int repeat) {StringBuilder sb = new StringBuilder();for (int i = 0; i < repeat; i++) {sb.append(input);}return sb.toString();}}}
// Diff package: com.otherpackage com.other;
import test.TargetClass;import java.util.List;
public class DiffPackageHandler {public static void handleContinue(TargetClass target) {class ContinueProcessor {public void process() {List<String> data = List.of("a", "b", "c");int index = 0;while (index < data.size()) {index++;// 3 super.field references (from TargetClass's parent context)if (target.baseValue == null) continue;if (target.counter < 5) continue;if (target.getDerivedValue().isEmpty()) continue;
System.out.println("Processed: " + data.get(index - 1));}}}new ContinueProcessor().process();}}