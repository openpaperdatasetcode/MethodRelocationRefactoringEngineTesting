package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface TestAnn {}
class ParentSuperClass {public void parentMethod() {}}
protected class TargetClass extends ParentSuperClass {String targetField;
public TargetClass() {Runnable r = new Runnable() {@Overridepublic void run() {}};}
class TargetInner {}}
class SourceParentClass {public abstract void methodToMove(TargetClass.TargetInner inner);}
class SourceClass extends SourceParentClass {class MemberInner {public int m1() { return 1; }public MemberInner m2() { return this; }public int m3() { return 3; }}
static class SourceStaticNested {}
class SourceInner {@TestAnn // has_annotation@Overrideprivate void methodToMove(TargetClass.TargetInner inner) {// Type declaration statementclass TypeDecl {}TypeDecl type = new TypeDecl();
// Variable call + Access instance methodTargetClass target = new TargetClass();String var = target.targetField;target.parentMethod();
// Requires try-catchtry {Class<?> cls = Class.forName("test.TargetClass");} catch (ClassNotFoundException e) {e.printStackTrace();}
// Assert statementassert var != null : "Var is null";
// Switch with instance method chainMemberInner innerObj = new MemberInner();switch (innerObj.m1()) {case 1:int result = innerObj.m1().m2().m3(); // 1 as requiredbreak;default:break;}}}}