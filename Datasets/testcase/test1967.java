package test;
interface MyInterface {}
sealed class SealedBase permits SourceClass {}
private class SourceClass extends SealedBase implements MyInterface {private TargetClass target;private int sourceField;
class MemberInner {}static class StaticNested {}
final int process() {int result = 0;TargetClass.LocalInner local = target.new LocalInner();for (TargetClass.InnerField field : target.getFields()) {result += field.value;if (field.value == 1) {break;}}int i = 0;while (i < 3) {result += calculate(i, "a", "b", "c");i++;}result += target.privateField;return result;}
protected final int calculate(int num, String... args) {if (num == 3) {return this.process();}return num + args.length;}}
private class TargetClass {private int privateField;
class InnerField {int value;}
List<InnerField> getFields() {class LocalInner {}return new ArrayList<>();}}