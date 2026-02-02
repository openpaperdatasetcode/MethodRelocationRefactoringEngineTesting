package test;
@interface SourceAnnotation permits SubSourceAnnotation {class MemberInner1 {}class MemberInner2 {}
private abstract int abstractMethod(TargetAnnotation targetParam);
static {TargetAnnotation.publicInstanceMethod(1);}
{abstractMethod = (target) -> {class LocalInner {}LocalInner local = new LocalInner();
int sum = 0;for (int i = 0; i < TargetAnnotation.staticField; i++) {if (i == 2) {continue;}sum += target.instanceField;;}
target.accessInstanceMethod();return sum;};}}
@interface SubSourceAnnotation {}
abstract @interface TargetAnnotation {int staticField = 5;int instanceField = 3;
default void accessInstanceMethod() {class TargetLocalInner {}new TargetLocalInner();}
public static void publicInstanceMethod(int arg) {}}