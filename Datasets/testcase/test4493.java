package test;
public enum TargetClass {INSTANCE;
int targetField = 10;
void targetMethod() {class TargetInnerRec {int getValue() {return targetField;}}}}
private enum SourceClass {VALUE;
TargetClass target = TargetClass.INSTANCE;
@MyAnnotation@AnotherAnnotationprotected int sourceMethod() {class LocalInner1 {int data = target.targetField;}class LocalInner2 {LocalInner1 inner = new LocalInner1();}LocalInner2 local = new LocalInner2();return local.inner.data;}
@interface MyAnnotation {}@interface AnotherAnnotation {}}