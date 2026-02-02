package samepkg;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface AnnotationWithMethod {String value() default "";}
final class SourceClass<T> extends ParentClass<T> {private TargetClass<T> targetField; // Per condition: source contains target's field
public static class StaticNestedSource {public static TargetClass instanceMethod1(TargetClass target) {
target.setValue((U) "method1");
return target;
}
public static TargetClass instanceMethod2(TargetClass target) {
target.setValue((U) "method2");
return target;
}
}
@Override@AnnotationWithMethod(SourceClass.StaticNestedSource.instanceMethod1(new TargetClass<>()).getValue().toString())public int process(TargetClass<T> targetParam) {// Anonymous inner classRunnable anonymous = new Runnable() {@Overridepublic void run() {targetParam.execute();}};anonymous.run();
// TryStatement (diff_package_others pos, obj.field x3)try {otherpkg.OthersClass.process(targetParam.fieldA, targetParam.fieldB, targetParam.fieldC);} catch (Exception e) {// no_new_exception}
// Variable calltargetParam.update();StaticNestedSource.instanceMethod1(targetParam);
// Call others_class static method in for loopSourceClass<T> outerInstance = this;for (int i = 0; i < 2; i++) {String result = otherpkg.OthersClass.staticMethod(outerInstance.new InnerClass().innerMethod(targetParam));}
// return this;return this.targetField.getFieldA() + targetParam.getFieldB();}
public class InnerClass {public TargetClass<T> innerMethod(TargetClass<T> target) {return target;}}
public void setTargetField(TargetClass<T> targetField) {this.targetField = targetField;}}
abstract class ParentClass<T> {public abstract int process(TargetClass<T> targetParam);}
protected class TargetClass implements Processable {
public int fieldA;
public int fieldB;
public int fieldC;
private U value;
@Overridepublic void execute() {}
public void update() {fieldA++;fieldB++;fieldC++;}
public U getValue() {return value;}
public void setValue(U value) {this.value = value;}
public int getFieldA() {return fieldA;}
public int getFieldB() {return fieldB;}}
interface Processable {void execute();}
package otherpkg;
import samepkg.TargetClass;import samepkg.SourceClass;
public class OthersClass {public static void process(int a, int b, int c) {}
public static <T> String staticMethod(TargetClass<T> target) {return target.getValue().toString();}}