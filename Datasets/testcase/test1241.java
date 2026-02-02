package test.pkg;
import java.util.ArrayList;import java.util.List;
public class SourceClass extends ParentClass {private String outerPrivateField = "sourcePrivate";public TargetClass targetInstance = new TargetClass();
public static class StaticNestedClass1 {public static void nestedMethod1() {}}
public static class StaticNestedClass2 {public static void nestedMethod2() {}}
/**
Varargs method to be moved, processes target class fields and inner classes.
@param args variable arguments
@return processed object
@throws IllegalArgumentException if arguments are invalid*/public final Object processVarargs(Object... args) throws IllegalArgumentException {if (args == null || args.length == 0) {throw new IllegalArgumentException("Arguments cannot be empty");}
TargetClass.StaticNestedClass targetNested = new TargetClass.StaticNestedClass();int count = 0;while (count < 1) {String targetField = targetInstance.targetField;targetNested.targetNestedMethod(targetField);
// Call target's normal method (3: count, target: owner, normal: type)targetInstance.targetNormalMethod(3, outerPrivateField, args[0]);
super.parentMethod();OtherObject.process(this);StaticNestedClass1.nestedMethod1();
count++;}
// Call sub-class method in LambdaRunnable runnable = () -> {SubClass sub = new SubClass();List<String> result = sub.subClassMethod(TargetClass.staticClassMethod("lambdaArg"));System.out.println(result);};runnable.run();
return args[0];}}
/**
Target class with Javadoc and static nested class.*/public class TargetClass {public String targetField = "targetFieldValue";
/**
Static nested class of TargetClass.
*/
public static class StaticNestedClass {
public void targetNestedMethod(String data) {}
}
/**
Normal instance method called by source method.
@param num count parameter (3)
@param privateData source's outer private field
@param arg method argument
*/
public void targetNormalMethod(int num, String privateData, Object arg) {}
/**
Static method called via ClassName.methodName().
@param arg input argument
@return processed string
*/
public static String staticClassMethod(String arg) {
return "static_" + arg;
}
// Method will be moved here:// /**// * Varargs method to be moved, processes target class fields and inner classes.// * @param args variable arguments// * @return processed object// * @throws IllegalArgumentException if arguments are invalid// */// public final Object processVarargs(Object... args) throws IllegalArgumentException { ... }}
class ParentClass {protected void parentMethod() {}}
class SubClass {/**
Sub-class instance method (call_method type: sub_class).
@param arg argument from TargetClass.staticClassMethod
@return list result
*/
List<String> subClassMethod(String arg) {
List<String> list = new ArrayList<>();
list.add(arg);
return list;
}
}
class OtherObject {public static void process(SourceClass source) {}}