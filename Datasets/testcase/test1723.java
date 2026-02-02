package test;
private enum SourceEnum {VALUE;
@Overrideprotected int toString(int param) { // Overriding method (assuming parent enum has this method)// Method types parameter is:nonevoid noParamsMethod() {}
// Access target fieldTargetEnum target = TargetEnum.TYPE;String fieldVal = target.targetField;
// Assert statementassert target != null : "Target must not be null";
// Expression statementint expr = target.ordinal();variableCall();
// Depends on inner classTargetEnum.MemberInner inner = target.new MemberInner();
// Call parent class constructor with superTypeReference in do-whileint count = 0;do {int result = ParentEnum.superTypeMethod(new ParentEnum.ConstructorParam());count++;} while (count < 3);
return 0;}
private void variableCall() {}}
non-sealed enum TargetEnum permits SubTargetEnum {TYPE;
String targetField;
class MemberInner {}}
enum SubTargetEnum implements TargetEnum {}
strictfp enum ParentEnum {PARENT_VALUE;
static class ConstructorParam {}
static strictfp int superTypeMethod(ConstructorParam param) {return param.hashCode();}
protected int toString(int param) {return param;}}