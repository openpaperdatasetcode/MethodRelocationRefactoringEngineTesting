package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorAnno {}
abstract class SuperParent {}interface TestInterface {}
abstract class SourceClass extends SuperParent implements TestInterface {private String instanceField = "sourceField";
// member inner class (source feature)class SourceInner {// source_inner_recclass SourceInnerRec {@RefactorAnno // has_annotationprotected TargetClass methodToMove(TargetClass.TargetParam param) { // contains target parameter (per_condition)// access_instance_fieldString fieldVal = SourceClass.this.instanceField;
// super constructor invocationSourceInner inner = new SourceInner() {};
// if statementif (param != null) {param.doAction();}
// switch caseint caseVal = 1;switch (caseVal) {case 1:fieldVal += "case1";break;default:break;}
// expression statementTargetClass target = new TargetClass();String expr = fieldVal + target.targetField;
// variable callSourceStaticNested staticNested = new SourceStaticNested();staticNested.nestedMethod();TargetClass.StaticNested targetNested = new TargetClass.StaticNested();targetNested.doSomething();
return target;}}}
// static nested class (source feature)static class SourceStaticNested {void nestedMethod() {}}}
public class TargetClass {public String targetField = "targetData";
// target_feature: static nested classpublic static class StaticNested {void doSomething() {}}
static class TargetParam {void doAction() {}}}