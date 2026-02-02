package test;
protected record SourceRecord(int sourceField) {// Member inner classclass SourceInner {void innerMethod() {}}
// Anonymous inner classRunnable anonymousRunnable = new Runnable() {@Overridepublic void run() {System.out.println(SourceRecord.this.sourceField);}};
// Refactored method (normal type, return base type, protected access)protected int moveMethod(TargetRecord targetParam, int keywordsParam) {// Super method invocation (matches "SuperMethodInvocation" exp, private modifier)String superResult = superToString();
// Variable call: access target's component (record field)int targetComponentVal = targetParam.targetComponent();// Access outer protected (source record's component)int outerVal = this.sourceField();
// ReturnStatement with "this.field" and "3" (target_feature), public modifier, pos: sourceif (keywordsParam == 3) {return this.sourceField();}
// Array initialization with method reference (call_method pos: array initialization)String[] strArray = new String[]{callMethod(), callMethod(1)};
// Final return statementreturn outerVal + targetComponentVal;}
// call_method: source type, private modifier, overloading, method reference supportprivate String callMethod() {return "default";}
private String callMethod(int num) {return String.valueOf(num);}
// Private method for SuperMethodInvocationprivate String superToString() {return super.toString();}}
// Target record class (public modifier, with static nested class)public record TargetRecord(int targetComponent) {// Static nested class (matches target_feature)public static class TargetStaticNested {void nestedMethod() {}}}