package test;
import java.lang.reflect.Method;
abstract class TargetParentClass {}
public abstract class TargetClass extends TargetParentClass {protected String targetProtectedField;
public static class TargetStaticNested {public void nestedMethod(TargetClass target) {target.targetProtectedField += "_nestedUpdated";}}
public TargetClass(String initField) {this.targetProtectedField = initField;}
public abstract void targetAbstractMethod();}
private abstract class SourceClass {private Object instanceMethod(TargetClass target) {TargetClass.TargetStaticNested staticNested = new TargetClass.TargetStaticNested();
class SourceLocalInner {void callSuperAndReflect(TargetClass tc) {variableCall(tc);staticNested.nestedMethod(tc);
try {Method abstractMethod = TargetClass.class.getMethod("targetAbstractMethod");System.out.println("Reflected abstract method: " + abstractMethod.getName());} catch (NoSuchMethodException e) {e.printStackTrace();}}}
class SourceMemberInner {public void useLocalInner(TargetClass tc) {new SourceLocalInner().callSuperAndReflect(tc);}}
new SourceMemberInner().useLocalInner(target);return target.targetProtectedField;}
private void variableCall(TargetClass target) {target.targetProtectedField = "source_updated_" + target.targetProtectedField;}}