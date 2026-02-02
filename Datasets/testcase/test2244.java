package test;
public class SourceClass {static class StaticNested {}
public void createLocalInner() {class LocalInner {}}
private Object methodToMove(TargetClass target) {private int var = 3;TargetClass.MemberInner inner = target.new MemberInner();int fieldVal = TargetClass.staticField;
while (var > 0) {fieldVal = inner.instanceField;var--;}
overloadMethod(inner);overloadMethod(3);
return inner;}
private void overloadMethod(TargetClass.MemberInner inner) {}private void overloadMethod(int num) {}}
protected class TargetClass {static int staticField = 3;
class MemberInner {int instanceField;}}