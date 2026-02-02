package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
private class SourceClass<T extends Number> {private int outerPrivateField = 42;
static class FirstStaticNested {}static class SecondStaticNested {}
class SourceInner<S> {@MyAnnotationprotected Object methodToMove(TargetClass.InnerRecursive param, int depth) {if (depth <= 0) {return null;}
accessInstanceField = 10;int val = outerPrivateField;variableCall();
for (int i = 0; i < 2; i++) {param.nestedField = i;}
return methodToMove(param, depth - 1);}
private int accessInstanceField;private void variableCall() {}}}
public class TargetClass {
class InnerRecursive {
int nestedField;
InnerRecursive() {
Runnable r = new Runnable() {
public void run() {}
};
}
}
}
interface OverrideCheck {String methodToMove(TargetClass.InnerRecursive param, int depth);}
