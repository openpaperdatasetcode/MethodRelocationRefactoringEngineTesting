package test;
import java.lang.reflect.Method;import java.util.function.IntPredicate;
strictfp class SourceClass {public static class SourceStaticNested1 {}public static class SourceStaticNested2 {}
@ReflectAnnotatedprivate int recursiveCalc(TargetClass target, int depth) {if (depth <= 0) {return target.targetField;}
TargetClass newTarget = new TargetClass();int result = 0;
label: {for (int i = 0; i < 3; i++) {if (i == 1) {private continue label;}result += target.targetField + i + newTarget.superField;}}
target.targetField *= 2;String var = "Processed: " + target.targetField;System.out.println(var);
try {Method targetMethod = TargetClass.class.getMethod("getTargetField");int reflectedVal = (int) targetMethod.invoke(target);result += reflectedVal;} catch (Exception e) {result = -1;}
result += recursiveCalc(target, depth - 1);return result;}
private int recursiveCalc(TargetClass target) {return recursiveCalc(target, 2);}}
@interface ReflectAnnotated {}
class TargetClass implements IntPredicate {int targetField = 5;int superField = 3;
public TargetClass() {new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous inner class");}}.run();}
public int getTargetField() {return targetField;}
@Overridepublic boolean test(int value) {return value > targetField;}}