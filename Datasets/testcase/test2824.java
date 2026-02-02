package test;
import java.io.IOException;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface CallAnnotation {Object value() default ParentClass.call(new TargetClass<>());}
private class SourceClass<T> implements Runnable {public class MemberInner {}
void methodToMove(TargetClass<T> targetParam) throws IOException {// VariableDeclarationStatement: this.field and 1public int targetFieldVal = targetParam.field;if (targetFieldVal != 1) throw new IOException("Field value mismatch");
// Enhanced for statement (corrected typo)List<T> dataList = targetParam.staticNested.getDataList();for (T data : dataList) {// TypeMethodReference with numbers=1 and private modifiertargetParam.staticNested.process(data, String::valueOf);}
// Local inner classclass LocalInner {void invokeTarget() {super.getClass(); // Super constructor invocationtargetParam.updateField(1); // Access_instance_methodvariableCall(targetParam); // Variable call}}new LocalInner().invokeTarget();
// Throw statementif (dataList.isEmpty()) throw new IllegalArgumentException("Data list is empty");}
private void variableCall(TargetClass<T> target) {target.staticNested.print(target.field);}
@Overridepublic void run() {}}
class ParentClass {public static <T> Object call(TargetClass<T> target) {return target.field;}}
public class TargetClass<T> {T field;public static final StaticNested<T> staticNested = new StaticNested<>();
public static class StaticNested {
public List getDataList() {
return List.of();
}
public void process(U data, java.util.function.Function<U, String> converter) {}
public void print(U data) {}}
public void updateField(T val) {this.field = val;}}
class SubParentClass extends ParentClass {@Overridepublic static <T> Object call(TargetClass<T> target) {target.updateField((T) "overridden");return target.field;}}