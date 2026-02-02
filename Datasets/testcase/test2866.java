package test;
import java.lang.reflect.Method;
public class SourceClass extends ParentClass {static class StaticNested extends ParentStaticNested {@Overrideprotected void overrideMethod() {} // Overriding method in inner class}
// Overload existspublic void methodToMove() {}
// Overriding method (implements parent's abstract method)@Overridepublic void methodToMove(TargetClass target) {class LocalInner {}LocalInner inner = new LocalInner();
// Uses outer thisSourceClass.this.toString();// Constructor invocationTargetClass.TargetStaticNested staticNested = new TargetClass.TargetStaticNested();// Variable call (source contains target's field)int val = target.targetField;// Expression statementval++;
try {// Used by reflectionMethod method = TargetClass.class.getMethod("methodToMove");// Overriding method reference in exception throwing statementsRunnable ref = StaticNested::overrideMethod;} catch (Exception e) {}}}
abstract class ParentClass {public abstract void methodToMove(TargetClass target);}
abstract class ParentStaticNested {protected abstract void overrideMethod();}
class TargetClass {int targetField = 2; // Target field used in sourcestatic class TargetStaticNested {}
public void methodToMove() {}}