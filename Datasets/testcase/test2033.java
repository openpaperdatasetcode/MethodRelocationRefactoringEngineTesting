package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface TestAnnotation {}
abstract class SourceClass {static int staticField;
private void methodToMove(TargetClass param) {@TestAnnotationclass LocalInner1 {}class LocalInner2 {}
int value = param.memberField;staticField = 10;super.toString();param.doSomething();}}
class TargetClass {int memberField;
void doSomething() {}
class MemberInner {}}