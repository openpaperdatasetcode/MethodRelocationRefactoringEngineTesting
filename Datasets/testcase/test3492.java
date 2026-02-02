package test;
non-sealed record SourceClass(String data) {TargetClass targetField = new TargetClass(3);
class MemberInner1 {class InnerRec {private Object moveMethod() {private int count = targetField.value();if (count == 3) {++count;variableCall();System.out.println(targetField.value());}return new Object();}
private void variableCall() {targetField.localInnerAction();}}}
class MemberInner2 {}}
protected record TargetClass(int value) {Object moveMethod() {class LocalInner {void useField() {System.out.println(this.value);}}new LocalInner().useField();return new Object();}
void localInnerAction() {class LocalInner {}}}
