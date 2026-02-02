package test.refactor.movemethod;
public class SourceClass {private String outerPrivateField = "outerPrivateValue";
// First static nested classpublic static class StaticNestedOne {public void nestedMethod(TargetClass target) {SourceClass source = new SourceClass();TargetClass result = source.overridingMethod(target);}}
// Second static nested class (duplicate feature as specified)public static class StaticNestedTwo {public TargetClass overridingMethod(TargetClass target) {// Overload exists (method with same name, different param type)return new TargetClass();}}
// Raw type field (complies with "raw_type" feature)private List rawTypeList = new ArrayList();
@Overrideprivate TargetClass overridingMethod(TargetClass targetParam) {class InnerClass {// Static ReturnStatement in inner class (complies with feature structure)static TargetClass staticReturnMethod() {TargetClass obj1 = new TargetClass();TargetClass obj2 = new TargetClass();TargetClass obj3 = new TargetClass();// 3 instances accessing obj.field (complies with "3" in target_feature)obj1.targetField = "value1";obj2.targetField = "value2";obj3.targetField = "value3";return obj1;}}
// Super constructor invocation (complies with "super constuctor invocation")class SubClassOfTarget extends TargetClass {public SubClassOfTarget() {super("superParam");}}
// Constructor invocation (complies with feature)SubClassOfTarget subTarget = new SubClassOfTarget();TargetClass newTarget = new TargetClass();
// Access outer class private field (complies with "access_outer_private")String accessedPrivate = SourceClass.this.outerPrivateField;
// Variable call (complies with feature)targetParam.targetField = accessedPrivate;subTarget.targetField = accessedPrivate;
// Raw type usage (complies with "raw_type")rawTypeList.add(targetParam);TargetClass rawTypeObj = (TargetClass) rawTypeList.get(0);
// Return target class type (complies with return_type requirement)return InnerClass.staticReturnMethod();}
// Overload exists (same method name, different parameter count)private TargetClass overridingMethod(TargetClass target1, TargetClass target2) {return new TargetClass();}}
public class TargetClass {public String targetField;
// Anonymous inner class (complies with target_feature)public TargetClass() {Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class in TargetClass");}};anonymous.run();}
// Super constructor (for super constructor invocation)public TargetClass(String param) {this.targetField = param;}}
import org.junit.Test;import static org.junit.Assert.*;import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
public class MoveMethodRefactoringTest5336 {@Testpublic void testMoveMethodRefactoring() throws Exception {SourceClass source = new SourceClass();TargetClass targetParam = new TargetClass();
// Test direct reflection call (since method is private)Method method = SourceClass.class.getDeclaredMethod("overridingMethod", TargetClass.class);method.setAccessible(true);TargetClass result = (TargetClass) method.invoke(source, targetParam);
assertNotNull(result);assertTrue(result.targetField != null);
// Test overload method invocationMethod overloadMethod = SourceClass.class.getDeclaredMethod("overridingMethod", TargetClass.class, TargetClass.class);overloadMethod.setAccessible(true);TargetClass overloadResult = (TargetClass) overloadMethod.invoke(source, targetParam, new TargetClass());assertNotNull(overloadResult);}}