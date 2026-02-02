package test;
abstract class SourceClass {public record SourceInnerRec() {private int methodToMove(TargetClass.TargetInner... targetInners) {int total = 0;
do {for (TargetClass.TargetInner inner : targetInners) {total += inner.value;
Object staticResult = TargetClass.staticMethod(inner, 10, "param");
if (inner.value > 5) {OtherPackage.StaticConstructorClass constructorObj =new OtherPackage.StaticConstructorClass(inner.thisField);}}} while (targetInners.length == 0);
return total;}}}
protected abstract class TargetClass implements Runnable {public class TargetInner {int value;String thisField;}
public static Object staticMethod(TargetInner inner, int num, String str) {return inner.value + num + str.length();}
@Overridepublic abstract void run();}
package OtherPackage;
import test.TargetClass.TargetInner;
public class StaticConstructorClass {public StaticConstructorClass(String field) {}}