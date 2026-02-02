package test;
interface FunctionalInterface1 {void apply(TargetClass tc);}
interface FunctionalInterface2 {int compute(int a, int b);}
protected class TargetClass {public static String field1 = "targetField1";public static int field2 = 200;public static boolean field3 = true;
Runnable targetAnonymous = new Runnable() {@Overridepublic void run() {System.out.println(field1 + field2 + field3);}};}
class SourceClass {Runnable sourceAnonymous1 = new Runnable() {@Overridepublic void run() {TargetClass tc = new TargetClass();tc.targetAnonymous.run();}};
FunctionalInterface1 sourceAnonymous2 = new FunctionalInterface1() {@Overridepublic void apply(TargetClass tc) {variableCall(tc);}};
TargetClass normalMethod(TargetClass target) {private abstract class AbstractLambdaHelper {abstract void helper(FunctionalInterface2 func);}
AbstractLambdaHelper helper1 = new AbstractLambdaHelper() {@Overridevoid helper(FunctionalInterface2 func) {int result = func.compute(10, 20);System.out.println(result);}};
AbstractLambdaHelper helper2 = new AbstractLambdaHelper() {@Overridevoid helper(FunctionalInterface2 func) {int result = func.compute(30, 40);System.out.println(result);}};
helper1.helper((a, b) -> a + b);helper2.helper((a, b) -> a * b);
assert target != null : "Target parameter cannot be null";assert TargetClass.field1 != null : "Target static field1 cannot be null";
for (int i = 0; i < 3; i++) {switch (i) {case 0 -> System.out.println(TargetClass.field1);case 1 -> System.out.println(TargetClass.field2);case 2 -> System.out.println(TargetClass.field3);}}
variableCall(target);sourceAnonymous2.apply(target);sourceAnonymous1.run();
return target;}
private void variableCall(TargetClass target) {target.targetAnonymous.run();System.out.println(TargetClass.field1 + "_updated");}}
