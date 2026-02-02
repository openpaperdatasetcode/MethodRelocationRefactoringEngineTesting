package test;
class ParentClass {}
abstract class SourceClass extends ParentClass {static class StaticNested {}private int outerField;
class MemberInner {protected TargetClass createTarget() {TargetClass target = new TargetClass() {{super(3);this.field = SourceClass.this.outerField;}};
TargetClass.StaticNested nested = new TargetClass.StaticNested();this.updateTarget(target);return target;}
private void updateTarget(TargetClass t) {t.field += 1;}}}
class TargetClass {int field;
TargetClass(int value) {this.field = value;}
static class StaticNested {}}