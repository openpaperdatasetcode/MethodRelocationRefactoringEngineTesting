package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface CustomAnnotation {}
abstract class SourceClass<T> {class MemberInnerSource {}
{// Anonymous inner class (source_feature)new Runnable() {};}
@CustomAnnotationpublic TargetClass.TargetInner methodToMove(TargetClass<T> target) {// Instance method from others_class in instance code blocks{TargetClass.TargetInner inner = this.helperMethod(target.new TargetInner());}
// Constructor invocationTargetClass.TargetInner newInner = target.new TargetInner();
// Variable callnewInner.toString();
return newInner;}
private TargetClass.TargetInner helperMethod(TargetClass.TargetInner inner) {return inner;}}
protected class TargetClass {
class TargetInner {
// Anonymous inner class (target_feature)
{
new Object() {};
}
}
}