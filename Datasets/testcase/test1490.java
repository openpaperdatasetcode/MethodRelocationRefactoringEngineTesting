package test.refactor.movemethod.source;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.function.Supplier;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
interface SourceInterface extends Supplier<Integer> {static class SourceStaticNested {public int getNestedValue() {return 42;}}
private default int refactorMethod(test.refactor.movemethod.target.TargetInterface<String> targetParam) {// Type declaration statementSourceStaticNested staticNested = new SourceStaticNested();
// Super constructor invocation (via anonymous inner class)Supplier<Integer> anonymous = new Supplier<>() {@Overridepublic Integer get() {return super.get(); // Super constructor invocation from functional interface}};
// VariableDeclarationStatement (transient, target_feature: this.field, 2, pos: diff_package_others)transient int field1 = ((test.refactor.movemethod.target.TargetInterface.StringStaticNested) targetParam.getNestedInstance()).nestedField1;transient int field2 = ((test.refactor.movemethod.target.TargetInterface.StringStaticNested) targetParam.getNestedInstance()).nestedField2;
// ParenthesizedExpression (numbers:1, modifier:default, exp:ParenthesizedExpression)int parenthesized = (staticNested.getNestedValue() + 8);
// Variable call & has_annotation@MethodAnnotationint variableCall = staticNested.getNestedValue() + parenthesized;
// Per_condition: contains target parameterint targetValue = targetParam.calculate((field1 + field2) * variableCall);
return targetValue;}
@Overridedefault Integer get() {return 0;}}
package test.refactor.movemethod.target;
import java.util.function.Supplier;
protected interface TargetInterface<T> {static class StringStaticNested {public int nestedField1 = 10;public int nestedField2 = 20;}
static class NumericStaticNested<T extends Number> {public T getValue() {return null;}}
default int calculate(int input) {return input * 2;}
default Object getNestedInstance() {return new StringStaticNested();}}
// Test case classpackage test.refactor.movemethod;
import test.refactor.movemethod.source.SourceInterface;import test.refactor.movemethod.target.TargetInterface;
public class MoveMethodTest5158 {public static void main(String[] args) {SourceInterface source = new SourceInterface() {};TargetInterface<String> target = new TargetInterface<>() {};
// Use reflection to invoke private default methodtry {java.lang.reflect.Method method = SourceInterface.class.getDeclaredMethod("refactorMethod", TargetInterface.class);method.setAccessible(true);int result = (int) method.invoke(source, target);
assert result == ((10 + 20) * (42 + 50)) * 2 : "Calculation result check";assert target.calculate(100) == 200 : "Target method check";} catch (Exception e) {assert false : "Reflection invocation failed: " + e.getMessage();}}}