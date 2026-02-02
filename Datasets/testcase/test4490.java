package test;
import java.util.ArrayList;import java.util.List;
final class SourceClass {private int outerPrivate = 5;private TargetClass targetField = new TargetClass();
static class SourceStaticNested1 {}static class SourceStaticNested2 {}
protected List<String> method() {List<String> result = new ArrayList<>();OthersClass others = new OthersClass();int count = 0;
while (count < 3) {int val;switch (count) {case 0:val = others.overloadMethod(targetField.field);break;case 1:val = others.overloadMethod(targetField.field, outerPrivate);break;case 2:val = others.overloadMethod(targetField, outerPrivate);break;default:val = 0;}result.add(String.valueOf(val));variableCall(targetField);count++;}return result;}
private void variableCall(TargetClass target) {int instanceVal = target.instanceField;target.memberInner.overrideViolationMethod();}}
final class TargetClass {int field = 1;int instanceField = 3;MemberInner memberInner = new MemberInner();
class MemberInner {public void overrideViolationMethod() {}}}
class OthersClass {public int overloadMethod(int a) {return a * 2;}
public int overloadMethod(int a, int b) {return a + b;}
public int overloadMethod(TargetClass target, int b) {return target.field + b;}}