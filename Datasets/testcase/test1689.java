package test;
import java.util.ArrayList;import java.util.List;
public class SourceClass {class MemberInner {}
int instanceMethod(TargetClass target) {super();class LocalInner {}
// ForStatement with super.field access (2 targets)private int sum = 0;for (int i = 0; i < 2; i++) {sum += target.superField1;sum += target.superField2;}
assert target != null;this.variableCall();variableCall();
// Raw type usageList rawList = new ArrayList();
return sum;}
private void variableCall() {}}
class TargetClass extends ParentClass {void someMethod() {class LocalInner {}}}
class ParentClass {protected int superField1;protected int superField2;}