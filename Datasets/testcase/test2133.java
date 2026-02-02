package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
class SuperSource {}
class SourceClass extends SuperSource {class Member MemberInner {}static class StaticNested {}
private Object methodToMove(TargetClass target) {try {for (int i = 0; i < TargetClass.staticField; i++) {target.variableCall();
if (target.field == null) {break;}}} catch (Exception e) {}
return overloadMethod(target) + overloadMethod(target.field);}
@MyAnnotationprivate Object overloadMethod(TargetClass target) {return target;}
private Object overloadMethod(Object field) {return field;}}
class TargetClass {static int staticField;Object field;static class StaticNested {}
void variableCall() {}}