package test.refactor.movemethod;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorAnnotation {}
// Target enum class (public modifier, static nested class)public enum TargetEnum {TARGET_INSTANCE(10);
public int targetField;
// Static nested class (target_feature)public static class TargetStaticNested {public void nestedMethod() {}}
TargetEnum(int targetField) {this.targetField = targetField;}
// Method to be overriddenprotected int baseMethod(TargetEnum target) {return target.targetField;}}
// Source enum class (default modifier, same package)enum SourceEnum implements TargetEnum.TargetStaticNested {SOURCE_INSTANCE;
public void sourceUsage() {// Local inner class (source feature)class LocalInner {public void useOverridingMethod(TargetEnum target) {new InnerClass().overridingMethod(target);}}
// Anonymous inner class (source feature)Runnable anonymous = new Runnable() {@Overridepublic void run() {new LocalInner().useOverridingMethod(TargetEnum.TARGET_INSTANCE);}};anonymous.run();}
// Inner class (method_position: source_inner)class InnerClass {// Overriding method (type: overriding)@RefactorAnnotation // has_annotation (duplicate as specified)@RefactorAnnotation@Overrideprotected int overridingMethod(TargetEnum targetParam) {// Depends on inner class (TargetEnum's static nested class)TargetEnum.TargetStaticNested nested = SourceEnum.SOURCE_INSTANCE;nested.nestedMethod();
// Constructor invocationTargetEnum newTarget = TargetEnum.TARGET_INSTANCE;
// Variable call (access target parameter's field)int paramValue = targetParam.targetField;
// SwitchStatement (public modifier, pos: same_package_target)public void switchLogic() {switch (paramValue) {case 10:// this.field (TargetEnum's instance field) + count 1this.paramValue = targetParam.targetField;break;default:break;}}switchLogic();
// No new exception thrownreturn paramValue;}
// Field for switch statement's this.field referenceprivate int paramValue;}}
import org.junit.Test;import static org.junit.Assert.*;import java.lang.reflect.Method;
public class MoveMethodRefactoringTest5339 {@Testpublic void testMoveMethodRefactoring() throws Exception {SourceEnum source = SourceEnum.SOURCE_INSTANCE;TargetEnum target = TargetEnum.TARGET_INSTANCE;SourceEnum.InnerClass inner = source.new InnerClass();
// Direct method callint result = inner.overridingMethod(target);assertEquals(10, result);
// Reflection test (verify annotations and overriding)Method method = SourceEnum.InnerClass.class.getDeclaredMethod("overridingMethod", TargetEnum.class);assertEquals(2, method.getAnnotations().length);assertTrue(method.isAnnotationPresent(RefactorAnnotation.class));assertEquals(int.class, method.getReturnType());
// Test source usage scenariosource.sourceUsage();}}