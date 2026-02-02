package test;
import java.lang.reflect.Method;
/**
Javadoc for TargetClass*/private class TargetClass {int targetField;
class TargetInner {int innerField;
final Object recursiveInnerMethod(int depth) {if (depth <= 0) {return new Object();}innerField = depth;return recursiveInnerMethod(depth - 1);}}}
protected class SourceClass extends ParentClass {private TargetClass.TargetInner targetInnerField = new TargetClass().new TargetInner();
class MemberInner {void memberMethod() {}}
public void createAnonymousInner() {Runnable r = new Runnable() {public void run() {}};}
public SourceClass() {super();}
@Overridepublic final Object recursiveMethod(int depth) {if (depth <= 0) {return new Object();}
super.parentField = 1;;
targetInnerField.innerField = depth;Object result = targetInnerField.recursiveInnerMethod(depth);
try {Method method = TargetClass.TargetInner.class.getMethod("recursiveInnerMethod", int.class);method.invoke(targetInnerField, depth - 1);} catch (Exception e) {}
return recursiveMethod(depth - 1);}}
class ParentClass {int parentField;}