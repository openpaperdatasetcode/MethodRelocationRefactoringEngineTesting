package test;
import java.io.IOException;
interface TestInterface {}
class SourceGenericClass<T extends Number> {class FirstSourceInner {}class SecondSourceInner {}
protected T sourceField;
Object instanceMethodToMove(TargetGenericClass<T> targetParam) {assert targetParam != null : "Target parameter must not be null";
FirstSourceInner inner1 = new FirstSourceInner();inner1.toString();
OtherPackageClass.declareVariable(this, targetParam);
targetParam.doMethod();T targetField = targetParam.targetField;System.out.println(targetField.intValue()); // Expression statement
return targetParam;}}
class TargetGenericClass<U extends Comparable> implements TestInterface {
class TargetInnerClass {}
U targetField;
void doMethod() {}}
package other;
import test.SourceGenericClass;import test.TargetGenericClass;import java.io.IOException;
public class OtherPackageClass {protected static <T extends Number> void declareVariable(SourceGenericClass<T> source, TargetGenericClass<T> target) {try {T var1 = source.sourceField;TargetGenericClass<T>.TargetInnerClass var2 = target.new TargetInnerClass();} catch (Exception e) {}}}
