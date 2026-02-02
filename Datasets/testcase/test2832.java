package test;
public class SourceClass implements Runnable {private static final int STATIC_FIELD = 1;
public class SourceInner {private void methodToMove(TargetClass targetParam, Integer... args) {try {if (targetParam == null) throw new IllegalArgumentException("Target cannot be null");
int count = 0;while (count < args.length) {int val = this.instanceMethod(targetParam.field, STATIC_FIELD);targetParam.field = val;variableCall(targetParam, args[count]);count++;}
new Runnable() {@Overridepublic void run() {super.toString(); // Super constructor invocationSystem.out.println(targetParam.field);}}.run();} catch (Exception e) {// Requires_try_catch handlinge.printStackTrace();}}
private int instanceMethod(int targetField, int staticVal) {return targetField + staticVal + 1;}
private void variableCall(TargetClass target, int arg) {target.field += arg;}}
@Overridepublic void run() {}}
/**
Javadoc for TargetClass: Private class with field used by source method
*/
private class TargetClass {
int field;
}
