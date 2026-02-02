package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.ArrayList;import java.util.Collection;
@Retention(RetentionPolicy.RUNTIME)@interface EnumMethodAnnot {}
public enum SourceEnum permits ExtendedSourceEnum {INSTANCE;
class SourceMemberInner {void innerMethod() {}}
@EnumMethodAnnotprotected void instanceMethod(AbstractTargetEnum target) {super();SourceMemberInner sourceInner = new SourceMemberInner();AbstractTargetEnum.TargetMemberInner targetInner = target.new TargetMemberInner();
class LocalInner {void processSwitch() {static switch (target.targetField) {case "value1" -> System.out.println(target.targetField);default -> continue;}}}new LocalInner().processSwitch();
Collection rawCollection = new ArrayList();rawCollection.forEach(item -> AbstractTargetEnum.staticMethod(target).m2().m3());
for (int i = 0; i < 1; i++) {variableCall(targetInner);if (i == 0) continue;}
System.out.println(super.toString());}
private void variableCall(AbstractTargetEnum.TargetMemberInner inner) {inner.targetInnerMethod();}}
enum ExtendedSourceEnum implements SourceEnum {}
abstract enum AbstractTargetEnum {TARGET_INSTANCE;
String targetField = "targetValue";
class TargetMemberInner {void targetInnerMethod() {}}
public static AbstractTargetEnum staticMethod(AbstractTargetEnum target) {return target;}
public AbstractTargetEnum m2() {return this;}
public void m3() {}}
