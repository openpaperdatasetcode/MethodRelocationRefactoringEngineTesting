package test.refactor.movemethod;
import java.util.ArrayList;import java.util.List;
// Target interface for implements featureinterface TargetInterface {void process(String data);}
// Target normal class (strictfp modifier, implements interface, static nested class)strictfp class TargetClass implements TargetInterface {public String targetField = "targetFieldValue"; // Target field (per_condition)
// Static nested class (target_feature)public static class TargetStaticNestedClass {public void nestedMethod(String input) {System.out.println("Target static nested class method: " + input);}}
public TargetClass() {} // Constructor for invocation
@Overridepublic void process(String data) {System.out.println("Target interface method: " + data);}}
// Source normal class (default modifier, same package, type parameter, two local inner classes)class SourceClass<T> {// First local inner class (source_class feature)public void firstLocalInnerHost() {class FirstLocalInner {public void innerMethod(T data) {System.out.println("First local inner class: " + data);}}new FirstLocalInner().innerMethod((T) "firstInnerData");}
// Second local inner class (source_class feature)public void secondLocalInnerHost() {class SecondLocalInner {public String getInnerData() {return "secondInnerData";}}System.out.println(new SecondLocalInner().getInnerData());}
// Varargs method to be refactored (public, return List<String>, method_position: source)public List<String> methodToRefactor(String... varargs) {List<String> result = new ArrayList<>();
// SwitchStatement (private, obj.field, 1, pos: diff_package_others)private TargetClass targetObj = new TargetClass();int switchVar = 1; // "1" featureswitch (switchVar) {case 1:result.add(targetObj.targetField); // obj.field (target class field)break;default:break;}
// Recursion method (type: recursion, default modifier, method_feature: 2, inner_class, recursion, new ClassName().method(), pos: annotation attribute)@FunctionalInterfaceinterface RecursionAnnotation {Object execute();}
@RecursionAnnotation(execute = () -> { // pos: annotation attribute values// Inner class usageSecondLocalInner recursionInner = new SecondLocalInner();result.add(recursionInner.getInnerData());
// "2" featureint recursionCount = 2;if (recursionCount > 0) {recursionCount--;return recursionMethod(recursionCount); // Recursion}
// new ClassName().method()new TargetClass.TargetStaticNestedClass().nestedMethod("recursionCall");return null;})class RecursionHost {}
// Constructor invocationTargetClass.TargetStaticNestedClass nestedInstance = new TargetClass.TargetStaticNestedClass();nestedInstance.nestedMethod("constructorInvocation");
// Variable call (target field + varargs + inner class)result.add(targetObj.targetField); // Target field (per_condition)for (String arg : varargs) {result.add(arg); // Varargs variable call}new FirstLocalInner().innerMethod((T) "variableCallInner");
// NullPointerExceptionString nullStr = null;try {result.add(nullStr.length()); // Potential NPE} catch (NullPointerException e) {result.add("NPE caught");}
// No new exception (only unchecked NPE, no new checked exceptions)return result;}
// Recursion helper methodprivate Object recursionMethod(int count) {if (count == 0) return "recursionEnd";return recursionMethod(count - 1);}
// Local inner classes accessible for recursion methodclass FirstLocalInner {public void innerMethod(T data) {result.add(data.toString());}}
class SecondLocalInner {public String getInnerData() {return "recursionInnerData";}}
// Helper list for recursion method accessprivate List<String> result = new ArrayList<>();}
// Test classpublic class MoveMethodRefactorTest_5213 {public static void main(String[] args) {SourceClass<String> source = new SourceClass<>();source.firstLocalInnerHost();source.secondLocalInnerHost();
List<String> refactorResult = source.methodToRefactor("vararg1", "vararg2");System.out.println("Refactored method result: " + refactorResult);}}