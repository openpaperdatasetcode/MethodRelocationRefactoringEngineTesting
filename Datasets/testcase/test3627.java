package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface OverrideAnnot {}
class ParentClass {protected Object overriddenMethod() {return null;}}
strictfp class SourceClass extends ParentClass {static class StaticNested {}
class MemberInner extends ParentClass {@Override@OverrideAnnotprotected Object overriddenMethod() {TargetClass target = new TargetClass();SourceClass.StaticNested nested = new SourceClass.StaticNested();
labeled: {for (int i = 0; i < 3; i++) {String result = target::synchronizedOverrideMethod;variableCall(target.memberInner);if (i == 1) break labeled;}}
expressionStatement(target);return SourceClass.this;}
private void variableCall(TargetClass.MemberInner inner) {inner.innerMethod();}
private void expressionStatement(TargetClass target) {target.instanceField = "updated";}}}
public class TargetClass {String instanceField;
class MemberInner {void innerMethod() {}}MemberInner memberInner = new MemberInner();
synchronized String synchronizedOverrideMethod() {return "targetMethod";}}