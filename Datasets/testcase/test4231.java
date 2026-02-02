package same.pkg;
public record TargetClass(int data) {class MemberInner {int getValue() {return data;}}}
protected record SourceClass(TargetClass targetField) {static class StaticNested {void process(SourceClass source) {int val = source.calculate();}}
public int calculate() {TargetClass target = new TargetClass(10);int result = target.data();class LocalInner {int useTarget() {return targetField.data();}}return new LocalInner().useTarget() + result;}}