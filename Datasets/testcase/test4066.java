package test;
public sealed class SourceClass permits SubSource {private TargetClass targetField;private int outerPrivateField = 5;
static class StaticNestedClass {private void recursiveMethod(int count) {if (count <= 0) {return;}
class LocalInner {void accessOuter() {System.out.println(outerPrivateField);}}LocalInner local = new LocalInner();local.accessOuter();
int var = targetField.targetField;for (int i = 0; i < var; i++) {if (i == 3) {break;}System.out.println(i);}
recursiveMethod(count - 1);}}}
final class SubSource extends SourceClass {}
protected class TargetClass {int targetField = 10;
void someMethod() {class TargetLocalInner {int value = 3;}TargetLocalInner tli = new TargetLocalInner();}}