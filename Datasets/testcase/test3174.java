package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface TestAnnotation {}
protected enum TargetEnum {INSTANCE;
String targetField;
TargetEnum() {Runnable r = new Runnable() {@Overridepublic void run() {}};}
public void instanceMethod() {}
public static TargetEnum staticMethod() {return INSTANCE;}}
class SamePackageOtherClass {private void process(TargetEnum target) {if (target != null) {TargetEnum.ClassName.field = 1;}}
static class ClassName {static int field;}}
private enum SourceEnum {INSTANCE;
class MemberInner {public TargetEnum chainMethod() {return TargetEnum.INSTANCE;}}
public void example() {class LocalInner {}}
@TestAnnotationTargetEnum methodToMove(TargetEnum target, String... args) {MemberInner inner = new MemberInner();TargetEnum var = target;
var.instanceMethod();
TargetEnum result = inner.chainMethod().chainMethod().chainMethod();
new SamePackageOtherClass().process(target);
TargetEnum assigned = SourceEnum::staticMethod;
return var;}
TargetEnum methodToMove(TargetEnum target, Integer... args) {return target;}
public static TargetEnum staticMethod() {return TargetEnum.INSTANCE;}}