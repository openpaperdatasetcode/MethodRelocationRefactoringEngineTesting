package test.refactor.movemethod;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorAnnotation {String callMethodAttr() default "this.callSynchronizedMethod(targetParam)"; // call_method pos: annotation attribute values}
// Others class for call_methodclass OthersClass {// call_method: type=others_class, modifier=synchronized, target_feature=[instance, this.methodName(arguments)]public synchronized TargetEnum callSynchronizedMethod(TargetEnum targetParam) {return targetParam;}}
// Parent enum for overridingenum ParentEnum {PARENT_INSTANCE;protected void overrideMethod(TargetEnum targetParam) {}}
// Source enum: private, same package with targetprivate enum SourceEnum extends ParentEnum {SOURCE_INSTANCE;
protected String outerProtectedField = "sourceProtected"; // for access_outer_protected
// Static nested class (source_class feature)public static class StaticNestedClass {public static void nestedMethod() {}}
@RefactorAnnotation // has_annotation@Override // method.type: overridingprotected void overrideMethod(TargetEnum targetParam) { // per_condition: contains target parameter// Do statementint count = 0;do {count++;} while (count < 3);
// Variable callStaticNestedClass.nestedMethod();AnonymousInnerClass anonymous = new AnonymousInnerClass() { // anonymous inner class (source_class feature)@Overridevoid doSomething() {SourceEnum.this.accessOuterProtected();}};anonymous.doSomething();
// Access outer protectedaccessOuterProtected();
// Call_method: this.methodName(arguments) via annotation attributeOthersClass others = new OthersClass();TargetEnum callResult = others.callSynchronizedMethod(targetParam);
// No new exception}
private void accessOuterProtected() {System.out.println(outerProtectedField);}
private interface AnonymousInnerClass {void doSomething();}}
// Target enum: public, target_feature=member inner classpublic enum TargetEnum {TARGET_INSTANCE1, TARGET_INSTANCE2;
// Member inner class (target_feature)public class MemberInnerClass {public void innerMethod() {}}
public MemberInnerClass memberInner = new MemberInnerClass();}
// Test classimport org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodRefactoring5310Test {@Testpublic void testOverridingMethodBeforeRefactoring() {SourceEnum source = SourceEnum.SOURCE_INSTANCE;TargetEnum targetParam = TargetEnum.TARGET_INSTANCE1;
// Execute target methodsource.overrideMethod(targetParam);
// Verify annotation presence and call_method attributevar method = SourceEnum.class.getDeclaredMethod("overrideMethod", TargetEnum.class);assertTrue(method.isAnnotationPresent(RefactorAnnotation.class));var annotation = method.getAnnotation(RefactorAnnotation.class);assertEquals("this.callSynchronizedMethod(targetParam)", annotation.callMethodAttr());
// Verify target_feature: member inner classassertNotNull(targetParam.memberInner);
// Verify call_methodOthersClass others = new OthersClass();TargetEnum callResult = others.callSynchronizedMethod(targetParam);assertSame(targetParam, callResult);}}