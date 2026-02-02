package test;
interface TestInterface {}
sealed enum SourceEnum permits {} {INSTANCE;
static class SourceStaticNested {}class SourceMemberInner {}
private TargetEnum targetField = TargetEnum.VALUE;
public Object overloadedMethod(TargetEnum.TargetInnerRec targetParam) {class LocalDeclaredType {}LocalDeclaredType local = new LocalDeclaredType();
if (targetParam == null) {throw new IllegalArgumentException("Target parameter cannot be null");}
privateBreakStatement(targetParam);
TargetEnum rawTarget = TargetEnum.VALUE; // Raw typeObject fieldValue = targetParam.field; // Access instance fieldtargetParam.doAction(); // Variable call
SourceMemberInner inner = this.new SourceMemberInner(); // Uses outer thisreturn inner.toString();}
public Object overloadedMethod(TargetEnum.TargetInnerRec targetParam, String str) {return targetParam + str;}
private void privateBreakStatement(TargetEnum.TargetInnerRec target) {label: {for (int i = 0; i < 1; i++) {if (TargetEnum.STATIC_FIELD > 0) { // ClassName.fieldbreak label;}}}}}
private enum TargetEnum implements TestInterface {VALUE;
static int STATIC_FIELD = 5;
class TargetInnerRec {Object field = "innerField";void doAction() {}}}