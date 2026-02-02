package test;
public class TargetClass {}
class SourceClass {private TargetClass targetField = new TargetClass();
static class SourceStaticNested {private void innerInstanceMethod() {System.out.println("Inner instance method executed");}}
class SourceMemberInner {public void callInnerMethod() {new SourceStaticNested().innerInstanceMethod();}}
public static int staticTestMethod(TargetClass targetParam) {int result = 0;SourceClass source = new SourceClass();
result = (targetParam != null) ? 1 : 0;new SourceMemberInner().callInnerMethod();
switch (result) {case 1:source.useTargetField(targetParam);break;case 0:result = -1;break;}
return result;}
private void useTargetField(TargetClass target) {this.targetField = target;}}