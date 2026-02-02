package test;
import otherpkg.OtherDiffPackageClass;import java.util.function.Function;
class ParentSourceClass {private String outerPrivateField = "privateData";protected String superField = "parentSuperField";
public void parentOverloadMethod(TargetClass target) {} public int parentOverloadMethod(TargetClass target, int num) { return num; }}
class SourceClass extends ParentSourceClass {class SourceMemberInner {protected <T extends CharSequence> TargetClass<T>.TargetInner overloadedMethod(TargetClass<T> targetParam) {new Runnable() {@Overridepublic void run() {targetParam.new TargetInner().doAction();}}.run();
OtherDiffPackageClass.staticLabeledMethod(targetParam);System.out.println(outerPrivateField); // Access outer private
TargetClass<T>.TargetInner inner = targetParam.new TargetInner();enhancedForLoop(inner);return inner;}
protected <T extends CharSequence> TargetClass<T>.TargetInner overloadedMethod(TargetClass<T> targetParam, String str) {do {this.overloadedHelper(targetParam);} while (str.isEmpty());return targetParam.new TargetInner();}
default void overloadedHelper(TargetClass<?> target) {super.parentOverloadMethod(target); // Super.methodName()}
private <T> void enhancedForLoop(TargetClass<T>.TargetInner inner) {for (String item : inner.getData()) {inner.process(item);}}}}
public class TargetClass extends ParentTargetClass {
class TargetInner {
void doAction() {
class TargetLocalInner {}
new TargetLocalInner();
}
String[] getData() { return new String[]{"a", "b"}; }void process(String s) {}}}
class ParentTargetClass<V> {protected V targetSuperField;}
package otherpkg;
import test.TargetClass;
public class OtherDiffPackageClass {static <T extends CharSequence> void staticLabeledMethod(TargetClass<T> target) {label: {System.out.println(target.targetSuperField); // Super.fieldbreak label;}}}
class CallerClass extends ParentSourceClass {int callParentMethod() {TargetClass<Integer> target = new TargetClass<>();Function<TargetClass<Integer>, Integer> func = t -> super.parentOverloadMethod(t, 10);return func.apply(target);}}