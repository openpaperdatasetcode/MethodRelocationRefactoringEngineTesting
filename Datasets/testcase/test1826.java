package test;
import java.sql.SQLException;import java.util.ArrayList;import java.util.List;import java.util.function.Function;
protected class SourceClass {public static class StaticNested {}
public record SourceInnerRec() {private Object varargsMethod(TargetClass... targets) throws SQLException {// Type declaration statementList<Object> results = new ArrayList<>();TargetClass.StaticNested nestedStatic = new TargetClass.StaticNested();
// Labeled statementprocessing:{// Constructor invocationTargetClass defaultTarget = new TargetClass();results.add(defaultTarget);
// Enhanced for statementfor (TargetClass target : targets) {// If statementif (target == null) {break processing;}
// Expression statementint value = target.instanceField + nestedStatic.staticField;results.add(value);
// Do statementint count = 0;do {value += count;count++;} while (count < 3);results.add(value);}}
// 2 private MethodInvocationsprivate int getTargetValue(TargetClass target) {return target.instanceField * 2;}private String formatValue(int val) {return "Formatted: " + val;}
// Variable calls for method invocationsfor (TargetClass target : targets) {results.add(getTargetValue(target));results.add(formatValue(target.instanceField));}
// Assert statementassert results.size() > 0 : "Results list is empty";
// Functional interface with call method from sub classFunction<TargetSubClass, Object> processor = TargetSubClass::processInstance;results.add(processor.apply(new TargetSubClass()));
return results;}}
public void useInnerRec() throws SQLException {// Local inner classclass LocalInner {void execute() throws SQLException {TargetClass target1 = new TargetClass();TargetClass target2 = new TargetClass();Object result = new SourceInnerRec().varargsMethod(target1, target2);System.out.println(result);}}new LocalInner().execute();}}
protected class TargetClass {int instanceField = 10;public static class StaticNested {int staticField = 5;}}
// Sub class of TargetClassprotected class TargetSubClass extends TargetClass {public Object processInstance() {return "Processed: " + instanceField;}}