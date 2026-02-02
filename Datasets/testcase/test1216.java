package test.refactoring.movemethod;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.InvocationTargetException;
/**
Strictfp source class with static nested class and member inner class features*/strictfp class SourceClass {// Source feature: static nested classpublic static class SourceStaticNestedClass {}
// Source feature: member inner classpublic class SourceMemberInnerClass {public void innerMethod() {}}
/**
Varargs method to be refactored (public access, returns List<String>)
@param targetParam target class parameter (per_condition)
@param varargs varargs parameters
@return List<String> result
@throws InvocationTargetException required checked exception (requires_throws)*/public List<String> refactorTargetMethod(TargetClass<String> targetParam, String... varargs) throws InvocationTargetException {List<String> result = new ArrayList<>();
// Variable callTargetClass<String> tempTarget = targetParam;
// Access instance field (target class instance field)result.add(tempTarget.getDataField());
// Expression statementtempTarget.invokeStaticNestedMethod();new SourceStaticNestedClass().toString();
// Switch statementswitch (varargs.length) {case 0:result.add("no_varargs");break;case 1:result.add("one_vararg: " + varargs[0]);break;default:result.add("multiple_varargs: " + varargs.length);break;}
// Exception handling statements (pos for constructor feature)try {// Constructor feature (type: constructor, modifier: public, return_type: Object)Object constructorResult = new ConstructorFeatureHelper(tempTarget, 2 // "2" in method_feature).createInstance();result.add(constructorResult.toString());} catch (IllegalAccessException e) {// Wrap exception to meet requires_throwsthrow new InvocationTargetException(e);}
return result;}
/**
Helper class for constructor feature (pos in exception handling statements)*/public class ConstructorFeatureHelper {private final TargetClass<String> target;private final int count;
// Constructor for feature integrationpublic ConstructorFeatureHelper(TargetClass<String> target, int count) {this.target = target;this.count = count;}
/**
Create instance via constructor + instanceReference.methodName(arguments)
@return Object instance
@throws IllegalAccessException for exception handling
*/
public Object createInstance() throws IllegalAccessException {
// "source" + "constructor" + "instanceReference.methodName(arguments)" features
TargetClass<String>.TargetStaticNested nested = target.new TargetStaticNested();
nested.nestedMethod(count); // instanceReference method call
return new TargetClass<>(String.valueOf(count)); // constructor invocation
}
}
}
/**
Private target class: target_feature: type parameter + static nested class
@param <T> type parameter (target_feature)*/private class TargetClass<T> {// Instance field for access_instance_field featureprivate T dataField;
public TargetClass(T data) {this.dataField = data;}
// Target feature: static nested classpublic static class TargetStaticNested {public <T> void nestedMethod(int count) {System.out.println("Target static nested class: count=" + count);}}
// Method to invoke static nested class (for expression statement)public void invokeStaticNestedMethod() {new TargetStaticNested().nestedMethod(0);}
// Getter for instance fieldpublic T getDataField() {return dataField;}}
// Container class to access private TargetClassclass TargetClassContainer {public <T> TargetClass<T> createTargetClass(T data) {return new TargetClass<>(data);}}
// Test class to verify functionalityclass SourceClassTest {public static void main(String[] args) throws InvocationTargetException {SourceClass source = new SourceClass();TargetClassContainer container = new TargetClassContainer();TargetClass<String> target = container.createTargetClass("test_data");
List<String> result = source.refactorTargetMethod(target, "arg1", "arg2");System.out.println("Refactor result: " + result);// Expected output: [test_data, multiple_varargs: 2, 2]}}