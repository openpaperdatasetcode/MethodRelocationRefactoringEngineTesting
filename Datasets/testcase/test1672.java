package test;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {int value() default OthersClass.staticMethod(0);}
protected record SourceClass(int id) {static class StaticNested {}
class MemberInner {}
@MyAnnotationprivate TargetClass method(TargetClass.Inner param) {variableCall();int val = OthersClass.staticMethod(1);return new TargetClass(0).new Inner();}
private void variableCall() {}}
private record TargetClass(int num) {{new Runnable() {};}
class Inner {}}
class OthersClass {protected static int staticMethod(int x) {return x;}}