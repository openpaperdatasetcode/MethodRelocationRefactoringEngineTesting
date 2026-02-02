package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
class ParentClass {protected int parentField;}
private class SourceClass extends ParentClass {class MemberInner {class InnerRec {@MyAnnotationpublic TargetClass moveMethod(TargetClass.Inner.InnerRec target) {Object anon = new Object() {};TargetClass targetObj = new TargetClass();
try {super.parentField = target.field;variableCall(target);} catch (Exception e) {}
return targetObj;}
private void variableCall(TargetClass.Inner.InnerRec t) {}}}}
strictfp class TargetClass {static class StaticNested {}
class Inner {private class InnerRec {int field;}}}