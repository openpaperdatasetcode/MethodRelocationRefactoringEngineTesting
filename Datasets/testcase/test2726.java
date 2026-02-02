package test;
strictfp class SourceClass {// Static nested class (source_feature)public static class SourceStaticNested {}
{// Anonymous inner class (source_feature)new Runnable() {@Overridepublic void run() {}};}
private TargetClass methodToMove(TargetClass target) {super(); // Super constructor invocation// Variable call + contains target parameter (per_condition)target.toString();
// ConstructorInvocation with super.field (count 1, pos: source)private TargetClass.StaticNested targetStatic = new TargetClass.StaticNested(target.superField);
// Depends on inner classTargetClass.Inner targetInner = target.new Inner();String innerField = targetInner.getInnerField();
// Expression statementinnerField = innerField.toUpperCase();targetInner.setInnerField(innerField);
// Break statementfor (int i = 0; i < 3; i++) {if (i == 1) break;targetStatic.appendData(innerField);}
try {if (target.superField == null) throw new NullPointerException();} catch (Exception e) {// No new exceptiontarget.superField = "default_super";}
return target;}}
public class TargetClass extends ParentTargetClass {public String superField = "target_super"; // Super.field (target_feature: extends)
// Static nested class (target_feature)public static class StaticNested {private String data;
public StaticNested(String initData) {this.data = initData;}
public void appendData(String s) {this.data += s;}}
// Inner class for depends_on_inner_class featurepublic class Inner {private String innerField = "target_inner"; // Source contains target's field (per_condition)
public String getInnerField() {return innerField;}
public void setInnerField(String innerField) {this.innerField = innerField;}}}
class ParentTargetClass {}
