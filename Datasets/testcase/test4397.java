package test;
public enum SourceEnum {// Enum constantsCONSTANT_ONE, CONSTANT_TWO;
// Member inner class (matches source_class.feature)class SourceMemberInner {int innerField;}
// Static nested class (matches source_class.feature)public static class SourceStaticNested {String nestedField;}
// Abstract method to refactor (matches method.type: abstract, return_type: void, access_modifier: private)private abstract void moveMethod(TargetEnum<String> targetEnum);
// Concrete implementation of abstract method (enum requires concrete methods for abstract declarations)static {CONSTANT_ONE.moveMethod = (target) -> {// EnhancedForStatement 1: matches nested EnhancedForStatement feature (protected modifier, obj.field, "1")protectedEnhancedFor(target);
// EnhancedForStatement 2: matches method.features "enhganced for statement"for (String item : target.enumField) {// Variable call: access target's fieldSystem.out.println(target.enumField.size() + item);}
// Throw statement (matches method.features)if (target == null) {throw new IllegalArgumentException("Target Enum cannot be null");}};
CONSTANT_TWO.moveMethod = (target) -> {protectedEnhancedFor(target);for (String item : target.enumField) {System.out.println(item);}};}
// Protected EnhancedForStatement method (matches nested feature's modifier and type)private static void protectedEnhancedFor(TargetEnum<String> target) {for (String val : target.enumField) {// obj.field access: target.enumField is accessed in loopif (val == null) break;}}
// Functional interface to hold abstract method implementation (enum workaround for abstract method per constant)private interface MethodHolder {void apply(TargetEnum<String> target);}
// Field to store abstract method implementationprivate MethodHolder moveMethod;}
// Target enum class: public, generic (type parameter) (matches target_class specs)public enum TargetEnum {
// Generic enum constants
TARGET_CONSTANT_ONE(List.of("a", "b")),
TARGET_CONSTANT_TWO(List.of("x", "y"));
// Field of target (matches per_condition "source contains the field of the target")public final List enumField;
// Target enum constructorTargetEnum(List enumField) {
this.enumField = enumField;
}
}