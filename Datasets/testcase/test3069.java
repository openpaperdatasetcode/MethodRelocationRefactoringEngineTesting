package test;
public class TargetClass extends ParentClass {int targetField;class TargetInner {}}
class SourceClass {class MemberInner {}
public SourceClass() {Runnable r = new Runnable() {@Overridepublic void run() {}};}
TargetClass methodToMove(TargetClass.TargetInner... params) {return methodToMove(new TargetClass(), params);}
TargetClass methodToMove(TargetClass target, TargetClass.TargetInner... params) {public class ConstructorHandler {ConstructorHandler() {this.field = target.targetField;target.targetField = 3;}int field;}new ConstructorHandler();
TargetClass.TargetInner inner = target.new TargetInner();int var = target.targetField;return target;}
private void callMethod(TargetClass target) {TargetClass.TargetInner inner = target.new TargetInner();Runnable r = (target != null) ? () -> methodToMove(inner).m1().m2().m3() : () -> {};}}
class ParentClass {public ParentClass m1() { return this; }public ParentClass m2() { return this; }public void m3() {}}
class SourceSubClass extends SourceClass {@OverrideTargetClass methodToMove(TargetClass.TargetInner... params) {return super.methodToMove(params);}}