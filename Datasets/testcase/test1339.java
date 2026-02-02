package test.refactor.movemethod.source;
import test.refactor.movemethod.target.TargetClass;import java.util.function.Predicate;
// Source class: public, different package with targetpublic class SourceClass extends ParentClass {private TargetClass targetField = new TargetClass() {}; // per_condition: source contains target's field
// Member inner class (source_class feature)public class MemberInnerClass {public void innerMethod() {}}
// Target method: instance, default access, return base typeint instanceMethod() {// Public AssertStatement (type=AssertStatement, modifier=public, target_feature=[ClassName.field, 1], pos=source)publicAssertStatement();
// Private static method feature (type=static, modifier=private, pos=expression, method_feature=[1, source, static, super.methodName()])privateStaticMethod();
// Expression statementString expr = TargetClass.STATIC_FIELD + "_expr";System.out.println(expr);
// PostfixExpression (numbers: "2", modifier=protected)int num = 2;protectedPostfixExpression(num);
// Variable callMemberInnerClass memberInner = new MemberInnerClass();memberInner.innerMethod();TargetClass.StaticNestedClass.staticNestedMethod();
// Local inner class (source_class feature)class LocalInnerClass {}LocalInnerClass localInner = new LocalInnerClass();
// With_bounds (generic type bounds)Predicate<String> boundedPredicate = (String s) -> s.length() > 0;
// No new exceptionreturn 42; // base type return}
// Overload exist (feature: overload_exist)int instanceMethod(String extra) {return extra.length();}
public void publicAssertStatement() {// target_feature: ClassName.field (TargetClass.STATIC_FIELD), 1assert TargetClass.STATIC_FIELD == 1 : "Static field mismatch";}
private static void privateStaticMethod() {// method_feature: 1, super.methodName()new ParentClass() {}.parentStaticMethod(1);}
protected void protectedPostfixExpression(int num) {// PostfixExpression with numbers: 2System.out.println(num++);}}
package test.refactor.movemethod.target;
// Interface for target_class implements featureinterface TargetInterface {void interfaceMethod();}
// Parent class for super.methodName()class ParentClass {public static void parentStaticMethod(int value) {}}
// Target class: abstract, different package with source, target_feature=[implements, static nested class]public abstract class TargetClass implements TargetInterface {public static final int STATIC_FIELD = 1; // for AssertStatement target_feature
// Static nested class (target_feature)public static class StaticNestedClass {public static void staticNestedMethod() {}}
@Overridepublic abstract void interfaceMethod();}
// Test classpackage test.refactor.movemethod;
import org.junit.Test;import static org.junit.Assert.*;import test.refactor.movemethod.source.SourceClass;import test.refactor.movemethod.target.TargetClass;
public class MoveMethodRefactoring5314Test {@Testpublic void testInstanceMethodBeforeRefactoring() {SourceClass source = new SourceClass();
// Execute target method (base type return)int result = source.instanceMethod();assertEquals(42, result);
// Test overload existint overloadResult = source.instanceMethod("test");assertEquals(4, overloadResult);
// Verify per_condition: source contains target's fieldtry {var field = SourceClass.class.getDeclaredField("targetField");field.setAccessible(true);assertNotNull(field.get(source));} catch (IllegalAccessException | NoSuchFieldException e) {fail("Failed to verify target field in source: " + e.getMessage());}
// Verify target_feature: implements and static nested classassertTrue(TargetInterface.class.isAssignableFrom(TargetClass.class));TargetClass.StaticNestedClass.staticNestedMethod(); // Verify static nested class}}