package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
interface MyInterface {}
public class TargetClass {String targetField;class TargetInner {}}
// Different packagepackage other;
import test.TargetClass;
public class OtherPackageClass {public void process(TargetClass target) {// ConstructorInvocation with obj.field and 1 in different packageTargetClass.TargetInner inner = target.new TargetInner();target.targetField = "1";}}
// Same packagepackage test;
class SourceClass implements MyInterface {class MemberInner {}
public void example() {class LocalInner {}}
/**
Method Javadoc
Processes TargetClass instance
@param target The TargetClass instance to process*/@MyAnnotation // has_annotationpublic final void methodToMove(TargetClass target) {// Variable callString var = target.targetField;
// Call to different package classnew other.OtherPackageClass().process(target);}}