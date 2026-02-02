package test;
import java.io.IOException;import java.lang.reflect.Method;
class SourceClass {protected TargetClass<String> targetField;
static class InnerOne {}
static class InnerTwo {final TargetClass<String> process(int... args) throws IOException {class Local {}int num = 2 + 3;Object obj = new OthersClass().protectedMethod();TargetClass<String> result = targetField;Method method;try {method = TargetClass.class.getMethod("method");} catch (NoSuchMethodException e) {throw new RuntimeException(e);}privateInstanceMethod();return (args.length > 0) ? superClassMethod() : targetField;}
private void privateInstanceMethod() {int a = 5;Object o = new Object();new SourceClass().targetField;superClassMethod();}
protected Object superClassMethod() {return super.toString();}}}
protected class TargetClass<T extends Number> extends SuperClass {@MyAnnotation(call = "protectedMethod")class LocalInner {}
void method() {}}
class OthersClass extends SuperClass {@Overrideprotected Object protectedMethod() {return super.protectedMethod();}}
class SuperClass {protected Object protectedMethod() {return null;}}
@interface MyAnnotation {String call();}