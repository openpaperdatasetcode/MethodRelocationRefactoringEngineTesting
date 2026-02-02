package test;
import other.DiffPackageTargetSubclass;import java.util.ArrayList;
final class SourceClass<T> {static class StaticNested1 {}static class StaticNested2 {}
private static Object methodToMove(TargetClass<T> target) {// Super constructor invocationRawType raw = new RawType();
// Do statement with continueint i = 0;do {if (i % 2 == 0) {continue;}target.new Inner().new InnerRecursive().variableCall();i++;} while (i < 3);
// Postfix expressions (numbers: 3)TargetClass<T>.Inner inner = target.new Inner();inner.counter++;inner.InnerRecursive rec = inner.new InnerRecursive();rec.value++;rec.nestedValue++;
// ExpressionStatement in different packageDiffPackageTargetSubclass<T> sub = new DiffPackageTargetSubclass<>();volatile boolean flag = (sub.this.field != null);
// Try statementtry {TargetClass.StaticNested.staticMethod();} finally {}
// Varargs in if/elseif (i > 0) {OthersClass.protectedVarargsMethod(rec.value, rec.nestedValue);} else {OthersClass.protectedVarargsMethod(0);}
// Instance code block with target method call{int result = TargetClass.strictfpMethod(new TargetClass<T>("init"));}
return null;}}
class RawType {}
class OthersClass {protected static <T> void protectedVarargsMethod(int... args) {}}
public class TargetClass<T> {T field;static class StaticNested {static void staticMethod() {}}
class Inner {int counter;class InnerRecursive {int value;int nestedValue;void variableCall() {}}}
public TargetClass(T field) {this.field = field;}
strictfp static <T> int strictfpMethod(TargetClass<T> target) {return target.field.hashCode();}}
package other;
import test.TargetClass;
public class DiffPackageTargetSubclass<T> extends TargetClass<T> {public DiffPackageTargetSubclass() {super(null);}}