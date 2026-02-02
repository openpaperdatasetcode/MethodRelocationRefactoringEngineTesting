package test;
protected record TargetClass(int targetField) {public void targetMethod() {class TargetLocalInner {void useField() {System.out.println(targetField);}}new TargetLocalInner().useField();}}
record SourceClass(String sourceData) {@MyAnnotationpublic static void staticMethod(TargetClass param) {class SourceLocalInner {void processTarget() {synchronized (param) {System.out.println(param.targetField());}}}
try {SourceLocalInner local = new SourceLocalInner();local.processTarget();param.targetMethod();} catch (Exception e) {}}
@MyAnnotationpublic static void staticMethod(int num) {System.out.println(num);}
public class SourceMemberInner {void invokeStaticMethod() {SourceClass.staticMethod(new TargetClass(15));}}
@interface MyAnnotation {}}