package same.pkg;
public class SourceClass {private int outerPrivateField = 5;private TargetClass targetField = new TargetClass();
class MemberInner1 {}class MemberInner2 {}
Object method() {variableCall();access_outer_private();
int i = 0;while (i < 3) {TargetClass.StaticNested nested = new TargetClass.StaticNested();nested.useOuterThis(SourceClass.this);i++;}
try {String value = targetField.targetField;} catch (NullPointerException e) {e.printStackTrace();}
return new Object();}
private void variableCall() {int localVar = outerPrivateField * 2;}
private void access_outer_private() {outerPrivateField += 10;}}
protected class TargetClass {String targetField = "targetValue";
static class StaticNested {void useOuterThis(SourceClass outer) {System.out.println(outer.outerPrivateField);}}}
