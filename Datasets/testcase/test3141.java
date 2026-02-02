package test;
import java.util.List;
class TargetParent {protected <T> void parentGenericMethod(T value) {}}
non-sealed class TargetClass extends TargetParent { // target_feature: extendsstatic class TargetStaticNested { // target_feature: static nested classstatic int field; // ClassName.field for AssertStatementclass TargetInner {} // target_inner}
public <T> void targetGenericMethod(T value) {super.parentGenericMethod(value); // super.methodName()}}
protected class SourceClass {static class SourceStaticNested {} // source_feature: static nested classclass SourceInner { // source_feature: member inner classstatic class InnerStatic {// AssertStatement (static modifier, ClassName.field = 1, pos: inner class)static <T> void checkField(TargetClass.TargetStaticNested nested) {assert TargetClass.TargetStaticNested.field == 1 : "Field mismatch";}}
// method types parameter is:genericprotected <T extends Number> int methodToMove(TargetClass.TargetStaticNested.TargetInner inner, List<T> list) {// Variable callTargetClass target = new TargetClass();int var = TargetClass.TargetStaticNested.field;
// With_boundsclass BoundedType {}
BoundedType<TargetClass> bounded = new BoundedType<>();
// Enhanced for statementint sum = 0;for (T num : list) {sum += num.intValue();}
// If/else conditions with instance method callif (sum > 0) {TargetClass instance = new TargetClass();TargetClass result = instance.new TargetStaticNested().new TargetInner().createTarget();}
// Exception handling statements with call_methodtry {target.targetGenericMethod(sum);} catch (Exception e) {target.targetGenericMethod("error");}
return sum + var;}}
// target instance method for method_featureclass TargetClass {TargetClass createTarget() {return new TargetClass();}}}