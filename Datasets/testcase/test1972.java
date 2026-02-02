package test;
public class SourceClass extends ParentClass {static class StaticNested {}class MemberInnerInner {@OverrideObject process() {TargetClass.Inner targetInner = new TargetClass.Inner();int count = 0;do {count += targetInner.value;targetInner.increment();} while (count < 5);return count;}}}
class ParentClass {Object process() {return null;}}
abstract class TargetClass {class Inner {int value;void increment() {value++;}}}