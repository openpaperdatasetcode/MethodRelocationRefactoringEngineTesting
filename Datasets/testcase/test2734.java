package test;
private class SourceClass {// Static nested classes (source_feature)public static class StaticNestedA {}public static class StaticNestedB {}
// Member inner class (source_inner)public class SourceInner {// Method types parameter: nonedefault int methodToMove(TargetClass<String> target) {super(); // Super constuctor invocation// Variable call + contains target parameter (per_condition)target.toString();TargetClass<String>.MemberInner inner = target.new MemberInner();int result = 0;
// Inner class with BreakStatement (this.field: 1)class InnerProcessor {public void calculate() {loop:for (int i = 0; i < 5; i++) {// Target's this.field accessif (inner.getField().equals("targetField")) {result = inner.getField().length() * i;break loop; // BreakStatement with this.field}}}}new InnerProcessor().calculate();
// Switch caseswitch (result) {case 0:result = 10;break;case 5:result *= 2;break;default:result += 5;}
// Expression statementresult += inner.getField().length();
// Depends on inner classinner.modifyField("modified_" + inner.getField());
try {if (inner.getField() == null) throw new NullPointerException();} catch (Exception e) {// No new exceptionresult = -1;}
return result;}}}
protected class TargetClass<T> {// Type parameter (target_feature)public class MemberInner {private T field = (T) "targetField"; // Source contains target's field (per_condition)
// this.field accessorpublic T getField() {return this.field;}
public void modifyField(T value) {this.field = value;}}}