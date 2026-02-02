package test;
public abstract class SourceClass {private TargetClass<String> targetField;private String privateOuterField = "privateValue";
class InnerClass1 {}class InnerClass2 {}
@MyAnnotationprivate TargetClass<String> getTargetField() {if (targetField == null) {throw new NullPointerException("Target field is null");}
ParentClass parent = new ParentClass();TargetClass<String> result;do {result = parent.synchronizedMethod(targetField);} while (result == null);
InnerClass1 inner1 = new InnerClass1();InnerClass2 inner2 = new InnerClass2();Runnable r1 = inner1::super.method;Runnable r2 = inner2::super.method;
targetField.doAction();System.out.println(this.privateOuterField);
return targetField;}
public void setTargetField(TargetClass<String> targetField) {this.targetField = targetField;}}
abstract class TargetClass<T> {void doAction() {class LocalInner {}}}
class ParentClass {public synchronized TargetClass<String> synchronizedMethod(TargetClass<String> target) {return target;}}
@interface MyAnnotation {}