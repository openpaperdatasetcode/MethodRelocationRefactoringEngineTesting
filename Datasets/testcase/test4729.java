package test;
class ParentClass {protected int outerProtected;public int method() { return 0; }}
final class SourceClass extends ParentClass {@Overridepublic int method() {TargetClass target = new TargetClass();this.var = target.field;Object outerThis = SourceClass.this;
private int count = 0;while (count < 1) {TargetClass.StaticNested.staticField = 1;count++;}
try {variableCall(target);} catch (Exception e) {}
return outerProtected;}
private int var;
private void variableCall(TargetClass t) {}}
class TargetClass {int field;static class StaticNested {static int staticField;}}