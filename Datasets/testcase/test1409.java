package test;
public abstract class SourceClass {// Static field for depends_on_static_fieldprivate static final String STATIC_DEP_FIELD = "static_dependency";
// Static nested class 1 (source feature)public static class SourceStaticNested1 {void nestedMethod1() {}}
// Static nested class 2 (source feature)public static class SourceStaticNested2 {void nestedMethod2() {}}
// Source contains target's field (per_condition)private TargetClass targetField = new TargetClass() {};
private TargetClass methodToMove(TargetClass.TargetParam... params) { // varargs, return TargetClass Type// super constuctor invocationSourceStaticNested1 nested1 = new SourceStaticNested1() {};
// variable callSourceStaticNested2 nested2 = new SourceStaticNested2();nested1.nestedMethod1();nested2.nestedMethod2();TargetClass target = targetField;TargetClass.MemberInner targetInner = target.new MemberInner();
// depends_on_static_fieldString staticVal = STATIC_DEP_FIELD;
// BreakStatement (protected, ClassName.field, 2, pos:source)for (int i = 0; i < 5; i++) {if (i == 2) {TargetClass.STATIC_FIELD = 2; // ClassName.field, 2break; // BreakStatement}}
// call_method: target, protected, normal, ClassName.methodName(arguments), pos:object initializationTargetClass.MemberInner innerInstance = target.new MemberInner();TargetClass.callProtectedMethod(innerInstance, params);
// return this; (adjusted to return target instance as method return type is TargetClass)return target;}}
public abstract class TargetClass {// ClassName.field for BreakStatement featurepublic static int STATIC_FIELD;
// target_feature: member inner class (target_inner)protected class MemberInner {void innerMethod() {}}
static class TargetParam {String getValue() { return "target_param"; }}
// call_method target: protected normal methodprotected static void callProtectedMethod(MemberInner inner, TargetParam... params) {if (params != null && params.length > 0) {inner.innerMethod();}}}
