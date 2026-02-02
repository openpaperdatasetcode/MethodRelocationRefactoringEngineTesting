package samepkg;
import java.lang.reflect.Method;
public record SourceClass(int sourceField) {public class SourceInnerClass {protected String outerProtectedField = "protectedValue";
public final static <T extends Number> void processTarget(TargetClass<T> targetParam) {// With bounds: T extends NumberT boundedVal = targetParam.value();int num = boundedVal.intValue();
// Access outer protected fieldString protectedVal = new SourceInnerClass().outerProtectedField;
// Variable call: target instance and static nested classTargetClass.StaticNested.staticMethod();targetParam.process(num);
// Overload exist (target has overloaded methods)targetParam.process(num, protectedVal);
// Used by reflectiontry {Method method = TargetClass.class.getDeclaredMethod("process", Number.class);method.setAccessible(true);method.invoke(targetParam, num);} catch (Exception e) {}}}}
package samepkg;
private record TargetClass<T extends Number>(T value) {public static class StaticNested {public static void staticMethod() {}}
// Overloaded methods (overload_exist)public void process(Number num) {}public void process(Number num, String str) {}}