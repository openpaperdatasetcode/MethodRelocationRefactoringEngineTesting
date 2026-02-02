package test;
import java.lang.reflect.Method;import java.io.IOException;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnot {String value() default "";}
strictfp class SourceClass<T> {class InnerClass {@MyAnnot(value = "#{callMethod()}")protected void callMethod() {TargetClass<String> target = new TargetClass<>();methodToMove(target);if (target.count > 0) {callMethod(); // Recursion}}}
protected void methodToMove(TargetClass<T> targetParam) throws IOException {// Super constructor invocationclass SubTarget extends TargetClass<T> {SubTarget() {super();}}SubTarget sub = new SubTarget();
// Expression statementtargetParam.process();
// Variable callT var = targetParam.value;int count = targetParam.count;
// Depends on inner classtargetParam.localInnerMethod();
// Used by reflectiontry {Method method = targetParam.getClass().getMethod("process");method.invoke(targetParam);} catch (Exception e) {throw new IOException(e);}}}
protected class TargetClass extends ParentClass {
U value;
int count = 3;
void process() {}
void localInnerMethod() {class LocalInner {void print(U val) {System.out.println(val);}}new LocalInner().print(value);}}
class ParentClass {}