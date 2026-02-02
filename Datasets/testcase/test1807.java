package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
public class SourceClass {public class MemberInner {}public static class StaticNested {}
void instanceMethod(TargetClass target) throws Exception {// ExpressionStatement with private modifier using 3 target fieldsprivate class PrivateExprContainer {void process() {int sum = TargetClass.fieldA + TargetClass.fieldB + TargetClass.fieldC;System.out.println(sum);}}new PrivateExprContainer().process();
// Ternary operator with instance methodList<String> result = target.getValue() > 0? TargetClass.targetMethod("arg1"): TargetClass.targetMethod("arg2", "arg3");
// Raw type usageArrayList rawList = new ArrayList();rawList.add(result);
// Super keywordSystem.out.println(super.getClass().getName());
// Variable call and access instance methodTargetClass.InnerRecord innerRec = target.new InnerRecord();int val = innerRec.overloadedMethod(10);val += innerRec.overloadedMethod("20");
// Used by reflectionMethod method = InnerRecord.class.getMethod("overloadedMethod", int.class);val += (int) method.invoke(innerRec, 30);
// Call method with method referenceRunnable r = InnerRecord::staticMethod;r.run();}}
public class TargetClass {public static int fieldA = 1;public static int fieldB = 2;public static int fieldC = 3;
int getValue() {return 5;}
public static List<String> targetMethod(String... args) {return new ArrayList<>(List.of(args));}
public record InnerRecord() {private int overloadedMethod(int num) {return num;}
private int overloadedMethod(String str) {return Integer.parseInt(str);}
private static int staticMethod() {// Local inner class in targetclass LocalInner {int getSum() {return fieldA + fieldB + fieldC;}}return new LocalInner().getSum();}}}