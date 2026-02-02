package test;
import java.lang.reflect.Field;
abstract class SourceClass {static class StaticNested {}
// Overloading methodspublic int overloadedMethod(AbstractTargetClass target) {return processTarget(target);}
public int overloadedMethod(AbstractTargetClass target, int param) {return processTarget(target) + param;}
private int processTarget(AbstractTargetClass target) {// Local inner class in sourceclass LocalInner {}
// Access target fieldint targetField = target.targetField;
// For statementfor (int i = 0; i < targetField; i++) {variableCall();}
// Super constructor invocationclass SubTarget extends AbstractTargetClass {SubTarget() {super();}}
// Used by reflectiontry {Field field = AbstractTargetClass.class.getDeclaredField("targetField");field.setAccessible(true);} catch (NoSuchFieldException e) {// Handle exception}
variableCall();
return targetField;}
private void variableCall() {}}
public abstract class AbstractTargetClass {int targetField;
void someMethod() {// Target feature: local inner classclass TargetLocalInner {}}}