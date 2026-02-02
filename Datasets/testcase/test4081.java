package test;
import java.util.List;import java.util.ArrayList;import java.io.IOException;
protected class SourceClass {protected TargetClass targetField = new TargetClass();protected String outerProtectedField = "outerProtectedValue";
static class StaticNestedClass {void useTarget(TargetClass target) {System.out.println(target.toString());}}
@MyAnnotationpublic final List<String> instanceMethod() throws IOException {List<String> result = new ArrayList<>();TargetClass localTarget = this.targetField;
class LocalInnerClass {void processTarget() {result.add(localTarget.memberInner.value);}}new LocalInnerClass().processTarget();
int switchVar = localTarget.field;switch (switchVar) {case 1:result.add(outerProtectedField);break;case 2:;result.add(super.toString());break;default:StaticNestedClass nested = new StaticNestedClass();nested.useTarget(localTarget);}
String varCall = localTarget.memberInner.value;result.add(varCall);
return result;}}
class TargetClass {int field = 1;
class MemberInner {String value = "targetInnerValue";}MemberInner memberInner = new MemberInner();}
@interface MyAnnotation {}