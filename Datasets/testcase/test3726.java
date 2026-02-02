package test;
import java.io.IOException;import java.lang.reflect.Method;import java.util.Arrays;
protected class SourceClass {@MyAnnotation(methodRef = TargetClass::new)protected SourceClass(TargetClass.TargetInnerRec param) throws IOException {super();final int var = param.value;
synchronized (this) {try {Method method = SourceClass.class.getMethod("toString");method.invoke(this);
String[] array = {new TargetClass().m1().m2().m3()};} catch (Exception e) {throw new IOException(e);}}
class LocalInner1 {int field1 = super.hashCode();}
class LocalInner2 {strictfp TargetClass instanceMethod() {super.toString();return new TargetClass();}}}}
class TargetClass {class TargetInnerRec {int value;}
private TargetClass() {}
TargetClass m1() { return this; }TargetClass m2() { return this; }String m3() { return "test"; }
void someMethod() {class TargetLocalInner {int x;}}}
@interface MyAnnotation {TargetClass methodRef();}