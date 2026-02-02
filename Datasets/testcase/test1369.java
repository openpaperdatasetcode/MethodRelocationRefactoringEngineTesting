package test.refactor.movemethod;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface TestAnnotation {}
// Source record class (default modifier, same package with target)record SourceRecord(String sourceField, TargetRecord targetField) {public void sourceMethod() {// First local inner classclass FirstLocalInner {public void useStaticMethod() {TargetRecord.MemberInner inner = new TargetRecord(10).new MemberInner();List<String> result = inner.callInnerMethod();}}
// Second local inner class (duplicate feature as specified)class SecondLocalInner {public void invokeStaticMethod() {TargetRecord target = SourceRecord.staticMethod(new TargetRecord(20), 5);}}
new FirstLocalInner().useStaticMethod();new SecondLocalInner().invokeStaticMethod();}
// Static method to be refactored (target: target_inner)@TestAnnotation // has_annotation featureprotected static <T extends TargetRecord> TargetRecord staticMethod(T targetParam, int count) {// With_bounds (type parameter T extends TargetRecord)T boundedTarget = targetParam;
// Variable call (access target's field)int targetFieldValue = boundedTarget.value();
// Call inner class method via method reference in while loopTargetRecord.MemberInner inner = boundedTarget.new MemberInner();while (count-- > 0) {List<String> result = inner.callInnerMethod();}
// No new exception thrownreturn boundedTarget;}}
// Target record class (no modifier, same package)record TargetRecord(int value) implements Runnable { // implements feature// Member inner class (target_feature)protected class MemberInner {// Call method (inner_class type, protected modifier)protected List<String> callInnerMethod() {// Target_feature: normal method + ClassName::methodName (method reference)List<String> list = new ArrayList<>();list.add(String.valueOf(TargetRecord.this.value()));Runnable runnable = TargetRecord::run;runnable.run();return list;}}
@Overridepublic void run() {// Implementation of Runnable}}
import org.junit.Test;import static org.junit.Assert.*;import java.lang.reflect.Method;
public class MoveMethodRefactoringTest5338 {@Testpublic void testMoveMethodRefactoring() throws Exception {TargetRecord target = new TargetRecord(30);SourceRecord source = new SourceRecord("test", target);
// Direct method callTargetRecord result = SourceRecord.staticMethod(target, 3);assertNotNull(result);assertEquals(30, result.value());
// Reflection call (verify annotation and accessibility)Method staticMethod = SourceRecord.class.getDeclaredMethod("staticMethod", TargetRecord.class, int.class);assertTrue(staticMethod.isAnnotationPresent(TestAnnotation.class));staticMethod.setAccessible(true);TargetRecord reflectResult = (TargetRecord) staticMethod.invoke(null, target, 2);assertEquals(target, reflectResult);
// Test source method usagesource.sourceMethod();}}