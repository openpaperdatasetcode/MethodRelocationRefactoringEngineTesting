package test;
abstract @interface TargetAnnotation {default void targetMethod() {class TargetLocalInner {boolean isTargetType(Object obj) {return obj instanceof TargetAnnotation;}}new TargetLocalInner();}}
@interface SourceAnnotation {class SourceInner1 {TargetAnnotation targetField = null;
@MyAnnotationprivate TargetAnnotation sourceMethod(Object param) {boolean check1 = param instanceof TargetAnnotation;boolean check2 = targetField instanceof TargetAnnotation;
try {if (!check1 || !check2) {SourceInner2.throwHelper();}targetField.targetMethod();} catch (Exception e) {}
return targetField;}}
class SourceInner2 {private final static void throwHelper() throws IllegalArgumentException {throw new IllegalArgumentException("Invalid TargetAnnotation instance");}
void invokeSourceMethod() {SourceInner1 inner1 = new SourceInner1();inner1.targetField = new TargetAnnotation() {};TargetAnnotation result = inner1.sourceMethod(inner1.targetField);}}
@interface MyAnnotation {}}