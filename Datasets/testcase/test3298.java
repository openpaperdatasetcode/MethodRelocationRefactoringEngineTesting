package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Function;
@Retention(RetentionPolicy.RUNTIME)@interface CallAnnotation {int value() default OthersClass.callMethod(SourceEnum.INSTANCE, TargetEnum.INSTANCE);}
class TargetParent {protected int superField = 1;}
protected enum SourceEnum<T extends Number> {INSTANCE;
class MemberInner {}
static class StaticNested {}
@Overridepublic final List<String> toString() { // Override violation (return type mismatch)List<String> result = new ArrayList<>();TargetEnum target = TargetEnum.INSTANCE;
// WhileStatement in inner classnew MemberInner() {void innerWhile() {int count = 0;while (count < 1) {if (target.super.field != 1) throw new NullPointerException();variableCall(target);count++;}}}.innerWhile();
// Lambda expression with method chainFunction<TargetEnum, TargetEnum> lambda = t -> t.innerClass.m1().m2().m3();lambda.apply(target);
// Access instance methodtarget.instanceMethod();return result;}
private void variableCall(TargetEnum target) {target.innerClass.doTask();}}
private enum TargetEnum extends TargetParent {INSTANCE;
class TargetInner {TargetEnum m1() { return TargetEnum.INSTANCE; }TargetEnum m2() { return TargetEnum.INSTANCE; }TargetEnum m3() { return TargetEnum.INSTANCE; }void doTask() {}}
private TargetInner innerClass = new TargetInner();
public void instanceMethod() {}}
class OthersClass {@CallAnnotationprotected static int callMethod(SourceEnum<?> source, TargetEnum target) {target.instanceMethod();return 0;}}