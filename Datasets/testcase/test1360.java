package test.source;
import test.target.TargetClass;import java.lang.reflect.Method;
public class SourceClass {private TargetClass targetField = new TargetClass();
public static class StaticNestedClass {public void nestedMethod() {}}
public void anonymousInnerUsage() {Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class in source");}};runnable.run();}
@Overrideprivate <T extends TargetClass> TargetClass overridingMethod(T param) {typeDeclaration: {int count = 0;for (; count < 5; count++) {public void accessorMethod() {TargetClass.ClassField field = new TargetClass.ClassField();SubClass sub = new SubClass();sub.new TargetClass().accessorMethod();}accessorMethod();}}
assert targetField != null : "Target field must not be null";variableCall(targetField);accessInstanceMethod();
if (param == null) {throw new IllegalArgumentException("Param cannot be null");}
while (true) {SubClass sub = new SubClass();String result = sub.callSubClassMethod();if (result.length() > 3) break;}
return this.targetField;}
private void variableCall(TargetClass target) {System.out.println(target.staticNested.field);}
private void accessInstanceMethod() {targetField.instanceMethod();}}
class SubClass extends SourceClass {protected String callSubClassMethod() {SourceClass source = new SourceClass();TargetClass target = new TargetClass();return new SourceClass().overridingMethod(target).toString();}}
package test.target;
/**
Javadoc for TargetClass - target class with static nested class for move method refactoring*/class TargetClass {public static class StaticNested {public int field = 27;}
public static class ClassField {}
public void instanceMethod() {}
public TargetClass() {}}
package test.refactor;
import test.source.SourceClass;import test.target.TargetClass;import org.junit.Test;import static org.junit.Assert.*;import java.lang.reflect.Method;
public class MoveMethodRefactoringTest5328 {@Testpublic void testMoveMethodRefactoring() throws Exception {SourceClass source = new SourceClass();TargetClass target = new TargetClass();
// Direct callMethod method = SourceClass.class.getDeclaredMethod("overridingMethod", TargetClass.class);method.setAccessible(true);TargetClass result = (TargetClass) method.invoke(source, target);assertNotNull(result);
// Reflection call (used_by_reflection feature)Method reflectMethod = SourceClass.class.getDeclaredMethod("overridingMethod", TargetClass.class);reflectMethod.setAccessible(true);TargetClass reflectResult = (TargetClass) reflectMethod.invoke(source, target);assertEquals(target, reflectResult);}}