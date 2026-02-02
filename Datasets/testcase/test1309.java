package test.refactor.movemethod;
protected class SourceClass {private TargetClass<String> targetField;
public static class StaticNestedClass {private static void nestedMethod() {}}
public TargetClass<String> publicMethodWithFeatures(int... varargs) {AnonymousInnerClass inner = new AnonymousInnerClass() {@Overridevoid doSomething() {SourceClass.StaticNestedClass.nestedMethod();}};inner.doSomething();
int count = 0;while (count < 3) {count++;}
if (targetField != null) {privateIfStatementLogic();}
ParentClass parent = new ParentClass();parent.parentMethod();super.toString();
if (varargs.length == 0) {throw new IllegalArgumentException();}
return targetField;}
private void privateIfStatementLogic() {String fieldValue = targetField.localInnerClassField;}
private interface AnonymousInnerClass {void doSomething();}}
private class TargetClass<T> {String localInnerClassField = "test";
class LocalInnerClass {void innerMethod() {}}
private static Object innerClassCallMethod() {int value = 1;switch (value) {case 1:return SourceClass.StaticNestedClass.nestedMethod();default:return new Object();}}}
class ParentClass {public void parentMethod() {}}
// Test classimport org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodRefactoringTest {@Testpublic void testMoveMethod() {SourceClass source = new SourceClass();TargetClass<String> result = source.publicMethodWithFeatures(1, 2, 3);assertNotNull(result);
Object innerCallResult = TargetClass.innerClassCallMethod();assertNull(innerCallResult);}}
