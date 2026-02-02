package test;
import java.lang.reflect.Method;
interface TargetInterface {}
abstract class ParentClass {}
class SourceClass extends ParentClass permits ChildClass1, ChildClass2 {private String sourceInstanceField = "sourceInstanceVal";static String sourceStaticField = "sourceStaticVal";
final Object recursiveMethod(TargetClass target, int depth) {if (depth <= 0) {return target.targetInstanceField;}
Object varCall = target.getInnerInstance().innerField;Object instanceFieldAccess = sourceInstanceField;Object staticFieldAccess = sourceStaticField;
try {Method targetMethod = TargetClass.class.getMethod("getTargetInstanceField");varCall = targetMethod.invoke(target);} catch (Exception e) {}
return recursiveMethod(target, depth - 1);}
void localInnerClassDemo() {class SourceLocalInner {}}
void anonymousInnerClassDemo() {Runnable r = new Runnable() {@Overridepublic void run() {System.out.println(sourceInstanceField);}};}}
class ChildClass1 extends SourceClass {}class ChildClass2 extends SourceClass {}
private class TargetClass implements TargetInterface {String targetInstanceField = "targetInstanceVal";
class TargetMemberInner {String innerField = "targetInnerVal";}
TargetMemberInner getInnerInstance() {return new TargetMemberInner();}
String getTargetInstanceField() {return targetInstanceField;}
void callMethodInPropertyAssignment() {AbstractInner abstractInner = (param) -> {SourceClass source = new SourceClass();return source.recursiveMethod(TargetClass.this, 3);};Object assignedValue = abstractInner.execute(targetInstanceField);}
abstract class AbstractInner {abstract Object execute(String param);}}
