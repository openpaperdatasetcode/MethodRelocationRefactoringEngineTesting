package test;
class ParentClass {int superField1;int superField2;}
public class SourceClass extends ParentClass {final void recursiveMethod(TargetClass target, int count) {if (count <= 0) return;
super.superField1 = 1;super.superField2 = 2;
Object obj = new Object() {{Runnable r = target::localMethod;}};
class LocalOne {void useTarget(TargetClass t) {t.field = count;}}new LocalOne().useTarget(target);
class LocalTwo {}new LocalTwo();
recursiveMethod(target, count - 1);}
final void recursiveMethod(TargetClass target) {recursiveMethod(target, 5);}}
abstract class TargetClass {int field;
void init() {class TargetLocal {void localMethod() {System.out.println(field);}}new TargetLocal().localMethod();}}