package test;
import otherpackage.ExternalClass;import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface ProcessAnnotation {}
private class SourceClass<T> {protected T outerProtected;
class MemberInner {}
static class StaticNested {}
@ProcessAnnotationprivate List<String> methodToMove(List<String> data, TargetClass<T> targetParam) {try {ExternalClass ext = new ExternalClass();private String extField = ext.targetRelatedField;
synchronized (targetParam) {targetParam.variableCall();targetParam.accessInstanceMethod();}
System.out.println(outerProtected);targetParam.new InnerClass().new InnerRecursive().process();
return new ArrayList<>(data);} catch (Exception e) {return new ArrayList<>();}}}
class TargetClass<T> {class InnerClass {class InnerRecursive {void process() {}}}
void variableCall() {}
void accessInstanceMethod() {}}
package otherpackage;
public class ExternalClass {public String targetRelatedField;}