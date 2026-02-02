package test;
import java.lang.reflect.Constructor;import java.lang.reflect.Field;import java.util.List;import java.util.function.Function;
abstract class SourceClass {protected int outerProtected = 2;
static class StaticNested1 {}static class StaticNested2 {}
class Inner {class InnerRec {protected TargetClass method(TargetClass target) throws ReflectiveOperationException {// Enhanced for statementfor (int num : target.StaticNested.numbers) {target.value += num;}
// Switch statementswitch (target.value) {case 1:target.value *= 2;break;default:target.value += outerProtected;}
// 2 TypeMethodReference with final modifierFunction<TargetClass, Integer> func1 = TargetClass.StaticNested::getCount;Function<TargetClass, Integer> func2 = TargetClass::calculate;int result = func1.apply(target) + func2.apply(target);
// EmptyStatement with volatile modifier, super.field=1 in inner classvolatile int emptyStmt; ;target.StaticNested.superField = 1;
// Variable call and access instance fieldtarget.StaticNested.data = "processed";int fieldValue = target.value;
// Access outer protected fieldtarget.value += outerProtected;
// Reflection usageConstructor<?> constructor = TargetClass.StaticNested.class.getConstructor();TargetClass.StaticNested nested = (TargetClass.StaticNested) constructor.newInstance();Field field = TargetClass.StaticNested.class.getField("superField");field.set(nested, 3);
// Call target overloading methods in property assignmenttarget.count = TargetClass.StaticNested.process(target, 5);target.count = TargetClass.StaticNested.process(target, "add");
return target;}}}}
strictfp class TargetClass extends ParentClass {int value;int count;
static class StaticNested {public static int superField;public static int[] numbers = {1, 2};public String data;
public static int getCount(TargetClass target) {return target.value;}
public static int process(TargetClass target, int num) {return target.value + num;}
public static int process(TargetClass target, String op) {return target.value + op.length();}}
public static int calculate(TargetClass target) {return target.value * 2;}}
class ParentClass {int superField;}