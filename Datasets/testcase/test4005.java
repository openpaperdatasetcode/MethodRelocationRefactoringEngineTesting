package test;
interface BaseInterface {}@interface ParentAnnotation {}
@interface SourceAnnotation extends ParentAnnotation implements BaseInterface permits SourceAnnotation {private String outerPrivateField = "outerPrivate";
void instanceMethod(TargetAnnotation target);
default void instanceMethod(TargetAnnotation target) {class LocalInner {}Runnable anon = new Runnable() { public void run() {} };
OthersClass obj = new OthersClass();try {obj.overload().overload(1).overload("str");} catch (Exception e) {}
assert target.value().equals("test");TargetAnnotation.StaticNested nested = new TargetAnnotation.StaticNested();String varCall = nested.field;String privateAccess = outerPrivateField;String instanceField = target.field;}}
public @interface TargetAnnotation {String value() default "test";String field = "targetField";
static class StaticNested {String field = "nestedField";}}
class OthersClass {OthersClass overload() { return this; }OthersClass overload(int i) { return this; }void overload(String s) {}}