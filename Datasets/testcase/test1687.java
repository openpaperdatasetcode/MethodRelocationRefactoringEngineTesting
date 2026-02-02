package test;
import java.lang.reflect.Method;import java.util.List;
abstract sealed class SourceClass<T extends Number> permits SubSourceClass {static class StaticNested {}
{new Runnable() {};}
protected int instanceMethod() {// ArrayCreation expressionString[] array = new String[1];
// Overriding method usage in for loopfor (int i = 0; i < 1; i++) {new TargetClass.MemberInner().overrideMethod();}
// Used by reflectiontry {Method method = SourceClass.class.getMethod("instanceMethod");} catch (NoSuchMethodException e) {}
variableCall();
// Call method in for loopfor (String s : List.of("a")) {TargetClass::strictfpMethod;}
return 0;}
private void variableCall() {}}
final class SubSourceClass extends SourceClass<Integer> {}
private class TargetClass {class MemberInner extends ParentInner {@Overridepublic void overrideMethod() {}}
strictfp void strictfpMethod() {}}
abstract class ParentInner {public void overrideMethod() {}}