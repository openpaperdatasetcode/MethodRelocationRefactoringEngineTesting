package test;
import java.util.List;
// Source record class: private, generic (type parameter), same package with targetprivate record SourceRecord<T extends Number>(T sourceComponent) {// Overriding method (matches method.type: overriding)@Overrideprivate Object toString() {return moveMethod(new TargetRecord<>("testTarget"));}
// Refactored method: overriding (implicit via Object), return Object, private accessprivate Object moveMethod(TargetRecord<String> targetParam) throws ClassCastException {// access_outer_private: access source record's private componentT outerPrivateVal = this.sourceComponent();
// accessor method in ternary operators (matches nested accessor feature)TargetRecord.TargetStaticNested nested = (outerPrivateVal != null)? targetParam.getNestedAccessor(): super.toString().isEmpty() ? new TargetRecord.TargetStaticNested() : null;
// enhanced for statementList<? extends Number> numList = List.of(1, 2, 3);for (Number num : numList) {// CastExpression with numbers:3 (3 cast operations)int cast1 = (int) num;long cast2 = (long) outerPrivateVal;double cast3 = (double) targetParam.targetComponent().length();
// if statement + variable call (target's component)if (cast1 > 1) {nested.process(cast3);}}
return outerPrivateVal;}}
// Target record class: public, with static nested class (target_feature)public record TargetRecord(U targetComponent) {
// Static nested class (matches target_feature)
public static class TargetStaticNested {
public void process(double val) {}
}
// Protected accessor method (matches accessor type in method.features)protected TargetStaticNested getNestedAccessor() {return new TargetStaticNested();}}