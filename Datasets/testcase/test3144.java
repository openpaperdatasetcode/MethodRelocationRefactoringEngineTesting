package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;
/**
TargetClass Javadoc
Generic class with member inner class and inner record
@param <T> Generic type parameter*/class TargetClass<T> {T thisField; // this.field for ConstructorInvocation
class TargetInner {record TargetInnerRec<T>(T val) {} // target_inner_rec}}
class SamePackageOthers {// ConstructorInvocation with this.field = 1public static <T> TargetClass<T> createTarget() {TargetClass<T> target = new TargetClass<>();target.thisField = (T) Integer.valueOf(1);return target;}}
class OtherClass {// Call_method: normal instance methodprivate List<String> otherMethod(TargetClass<?> target) {List<String> list = new ArrayList<>();list.add(target.thisField.toString());return list;}}
public class SourceClass {
class MemberInner {} // member inner class
static class StaticNested {} // static nested class
strictfp TargetClass methodToMove(TargetClass.TargetInner.TargetInnerRec rec, U... args) {
// Variable call
U var = rec.val();
TargetClass target = new TargetClass<>();
target.thisField = var;
// ConstructorInvocation (pos: same_package_others)TargetClass otherTarget = SamePackageOthers.createTarget();
// Assert statementassert otherTarget.thisField != null : "Field should not be null";
// NullLiteral with numbers:1U nullVal = null;if (nullVal == null) {target.thisField = args.length > 0 ? args[0] : null;}
// Functional interfaces with call_methodOtherClass other = new OtherClass();Supplier<List<String>> supplier = () -> other.otherMethod(target);List<String> resultList = supplier.get();
return target;}}