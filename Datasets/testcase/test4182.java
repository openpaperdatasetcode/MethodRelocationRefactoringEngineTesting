package test;
@interface ParentTargetAnnotation {}
protected @interface TargetAnnotation extends ParentTargetAnnotation {String value() default "targetVal";
class TargetInner {String innerField = "targetInnerField";
TargetInner(String field) {this.innerField = field;}
String getInnerField() {return innerField;}}}
@interface SourceAnnotation {class SourceMemberInner {protected static Object staticMethod(TargetAnnotation.TargetInner targetInner) {TargetAnnotation.TargetInner newInner1 = new TargetAnnotation.TargetInner("new1");TargetAnnotation.TargetInner newInner2 = new TargetAnnotation.TargetInner("new2");
String varCall = targetInner.getInnerField();if (varCall.isEmpty()) {return overloadMethod("default");}
dependsOnInnerClass(targetInner);return overloadMethod(varCall);}
private static String overloadMethod(String param) {return "overload1:" + param;}
private static Object overloadMethod(TargetAnnotation.TargetInner inner) {return "overload2:" + inner.innerField;}
private static void dependsOnInnerClass(TargetAnnotation.TargetInner inner) {System.out.println("Depends on: " + inner.innerField);}}
default void anonymousClassDemo() {Runnable r = new Runnable() {@Overridepublic void run() {SourceMemberInner.staticMethod(new TargetAnnotation.TargetInner("anon"));}};}}