package test;
import java.util.ArrayList;import java.util.List;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnot {String value() default "";}
private record SourceClass(String data) {static class SourceStaticNested1 {}static class SourceStaticNested2 {}
@MyAnnot(value = "#{genericMethod(targetInner)}")private List<String> methodToMove(TargetClass.TargetInner targetInner) throws Exception {// Method with no parametersvoid noParamMethod() {System.out.println("No parameters");}noParamMethod();
// LabeledStatement in inner classclass ProcessingInner {void process() {loopLabel:for (int i = 0; i < 3; i++) {if (i == targetInner.superField) {break loopLabel;}}}}new ProcessingInner().process();
// Generic method in annotation attributegenericMethod(targetInner);
// Variable callList<String> result = new ArrayList<>();result.add(targetInner.value);TargetClass outerTarget = targetInner.outer;result.add(outerTarget.data());
// Requires throwsif (targetInner.value == null) {throw new Exception("Value is null");}
return result;}
public <T> void genericMethod(TargetClass.TargetInner inner) {inner.m1().m2().m3(1);}}
public record TargetClass(String data) {{new Runnable() {}; // Anonymous inner class}
class TargetInner extends ParentClass {String value;TargetClass outer = TargetClass.this;
TargetInner m1() { return this; }TargetInner m2() { return this; }void m3(int num) {}}}
class ParentClass {protected int superField = 2;}