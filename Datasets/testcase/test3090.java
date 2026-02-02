package test;
abstract class TargetClass<T> {T targetField;class TargetInnerRec {}}
abstract class SourceClass<T> {class MemberInner {}
void example() {class LocalInner {}}
Object methodToMove(TargetClass<T>.TargetInnerRec param) {private class ThrowHandler {void check(TargetClass<T> target) {if (target.targetField == null) {throw new IllegalArgumentException("Field is null"); // ThrowStatement with obj.field and 1}}}
TargetClass<T> target = new TargetClass<T>() {};new ThrowHandler().check(target);
Object var = target.targetField; // Variable calltarget.targetField = (T) "new value"; // Expression statement
try {if (var == null) {throw new RuntimeException(); // Throw statement}} catch (Exception e) {}
return var;}}