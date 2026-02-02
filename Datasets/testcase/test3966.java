package test;
public enum TargetClass extends ParentEnum {INSTANCE;
static class TargetNested {int value;}
TargetClass() {}}
sealed enum SourceClass permits SourceClass.Val {VAL;
private TargetClass.TargetNested targetField;
static class SourceNested {String data;}
private SourceClass() {synchronized (this) {TargetClass.TargetNested localVar;localVar = targetField;localVar.value = 10;}}
void createLocalInner() {class LocalInner {int num;}}}
class ParentEnum {}