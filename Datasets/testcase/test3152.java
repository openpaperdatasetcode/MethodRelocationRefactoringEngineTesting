package source;
import target.TargetClass;import java.lang.reflect.Method;import java.lang.reflect.Type;import java.lang.reflect.ParameterizedType;
class SourceClass {static class SourceStaticNested {}
public void example() {class LocalInner {}}
// Overloading method 1protected int methodToMove(TargetClass target) {// Variable callint var = target.targetField;
// Expression statementtarget.targetField = var + 1;
// Do statementint count = 0;do {count++;} while (count < 3);
// Try statementtry {// Used by reflectionMethod method = TargetClass.class.getMethod("instanceMethod");method.invoke(target);
// TypeLiteral with numbers:1TypeLiteral<String> typeLiteral = new TypeLiteral<String>() {};} catch (Exception e) {e.printStackTrace();}
// Call_method in if/else conditionsObject result;if (var > 0) {result = OtherClass.instanceMethod(target);} else {result = OtherClass.instanceMethod(target, "default");}
return target.targetField;}
// Overloading method 2protected int methodToMove(TargetClass target, String arg) {target.targetField = Integer.parseInt(arg);return target.targetField;}
// Private TypeLiteral classprivate abstract class TypeLiteral<T> {public Type getType() {return ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];}}}
package target;
/**
TargetClass Javadoc
Abstract normal class with member inner class*/abstract class TargetClass {int targetField;
/**
TargetInner Javadoc
Member inner class of TargetClass
*/
class TargetInner {}
public void instanceMethod() {}}
package source;
import target.TargetClass;
public class OtherClass {public static Object instanceMethod(TargetClass target) {return target.targetField;}
public static Object instanceMethod(TargetClass target, String arg) {return arg + target.targetField;}}