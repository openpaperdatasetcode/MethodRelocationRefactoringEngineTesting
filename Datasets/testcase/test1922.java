package test;
public class SourceClass {class MemberInner {TargetClass process(TargetClass target, int value) {if (value <= 0) {return target;}return process(target, value - 1);}}
@MyAnnotationprotected TargetClass method(TargetClass targetParam) {class LocalInner {int recursiveSum(int n) {if (n == 0) {return 0;}return n + recursiveSum(n - 1);}}
try {assert targetParam != null : "Target cannot be null";
new TargetClass();TargetClass result = new MemberInner().process(targetParam, 2);
int i = 0;do {targetParam.value += i;i++;} while (i < 3);
if (targetParam.value < 0) {throw new IllegalArgumentException("Negative value");}
return result;} catch (Exception e) {throw new RuntimeException(e);}}
protected TargetClass method(TargetClass targetParam, String msg) {return targetParam;}}
/**
Target class with value field
Used for testing recursion and method overloading*/public class TargetClass {int value;
TargetClass() {}
int superMethod() {return super.hashCode();}}
@interface MyAnnotation {}