package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
public class SourceClass<T> {class FirstInner {class InnerRecursive {@MyAnnotationprotected Object methodToMove(TargetClass<String> targetParam) {TargetClass<String>.MemberInner.InnerRecursive innerRec = targetParam.new MemberInner().new InnerRecursive();this.process();
innerRec.variableCall();return innerRec.data;}
private void process() {}}}}
non-sealed class TargetClass<S> {class MemberInner {class InnerRecursive {S data;void variableCall() {}}}}