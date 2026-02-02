package test;
import java.lang.reflect.Method;
/**
Javadoc for TargetEnum
*/
enum TargetEnum {
VALUE1, VALUE2;
static class NestedStatic {}
int targetField;
}
public enum SourceEnum implements Runnable {ENUM1, ENUM2;
class MemberInner {}
static {public abstract class AbstractStaticInner {abstract Object abstractMethod();Object callSuperType() {return Runnable.super.toString();}}new AbstractStaticInner() {@OverrideObject abstractMethod() {return new Object();}};}
public SourceEnum() {Runnable r = new Runnable() {@Overridepublic void run() {}};}
@MyAnnotationprotected void methodToMove(TargetEnum target) {class TypeDecl {}TypeDecl type = new TypeDecl();
int var = target.targetField;target.targetField = 2;
OtherProcessor processor = new OtherProcessor();processor.process(this);
try {Method method = SourceEnum.class.getMethod("methodToMove", TargetEnum.class);method.invoke(this, target);} catch (Exception e) {}
for (int i = 0; i < 3; i++) {if (i == 1) {break;}if (i == 2) {return;}}}
@Overridepublic void run() {}
public void callViaTernary(TargetEnum target) {TargetEnum result = (target == null) ? ParentClass.staticMethod() : TargetEnum.VALUE1;}}
class OtherProcessor {void process(SourceEnum source) {}}
class ParentClass {private static TargetEnum staticMethod() {return Runnable.super.toString() != null ? TargetEnum.VALUE2 : TargetEnum.VALUE1;}}
@interface MyAnnotation {}
// Simulated different package class (same package for structure compliance)class DiffPackageSim {static {new TargetEnum.NestedStatic();}}
