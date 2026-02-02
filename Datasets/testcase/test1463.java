package test.refactoring;
// Source class: generic, abstract, same package, has static nested/member inner classabstract class GenericSourceClass<T> {// Source contains target's field (per_condition)private GenericTargetClass<String> targetField = new GenericTargetClass<>();protected T outerProtectedField; // For access_outer_protected
// Static nested class (source feature)public static class SourceStaticNested {}
// Member inner class (source feature)public class SourceMemberInner<V> {}
// Target method: instance, Object, private, source positionprivate Object moveTargetMethod() {T var = outerProtectedField; // variable call + access_outer_protectedObject result = var;
// While statementint i = 0;while (i < 3) {result = targetField.targetInnerClass.new TargetInnerClass().targetInnerField; // variable calli++;}
// No new checked exceptionreturn result;}}
// Target class: generic, private, has anonymous inner class (target_feature)private class GenericTargetClass<S> {// Target field referenced by source (per_condition)public String targetField = "target_generic_field";
// Member inner class (target_inner: method's target position)public class TargetInnerClass {public S targetInnerField;
public TargetInnerClass() {this.targetInnerField = (S) "inner_field_value";}}
// Target inner class instancepublic TargetInnerClass targetInnerClass = new TargetInnerClass();
// Anonymous inner class (target_feature)private Runnable targetAnonymous = new Runnable() {@Overridepublic void run() {System.out.println(targetField);}};}