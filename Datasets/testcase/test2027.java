package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
public class SourceClass {static class StaticNested {class SourceInner {@MyAnnotationpublic static Object staticMethod(TargetClass.TargetInner inner) {variableCall = inner.field;
switch (inner.field) {case 1:break;default:break;}
return variableCall;}
static TargetClass.TargetInner variableCall;}}
void methodWithLocal() {class LocalInner {}}}
class TargetClass {class TargetInner {int field;}}