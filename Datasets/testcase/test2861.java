package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface CustomAnnotation {}
private class SourceClass {protected int outerProtectedField = 10;
class MemberInner {private TargetClass recursiveMethod(TargetClass.Nested nestedParam) {return new LocalInner().process(nestedParam);}
class LocalInner {@CustomAnnotationprivate TargetClass process(TargetClass.Nested param) {label: {if (param == null) break label;variableCall(param);int value = outerProtectedField;}return new TargetClass();}
private void variableCall(TargetClass.Nested target) {TargetClass.Nested local = target;}}}}
final class TargetClass {static class Nested {}}