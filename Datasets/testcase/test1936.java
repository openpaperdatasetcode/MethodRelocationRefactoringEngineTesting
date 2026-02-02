package test;
import java.util.ArrayList;import java.util.List;
protected class SourceClass {static class StaticNested1 {}static class StaticNested2 {}
private TargetClass method(TargetClass target) {// Constructor invocation with ClassName.field=1TargetClass.Child child = new TargetClass.Child(TargetClass.STATIC_FIELD);
// Raw type usageList rawList = new ArrayList();rawList.add(target.field);
// Instance method calls in constructor parameter list (3 times)TargetClass wrapper = new TargetClass(new ParentClass().createTarget(),new ParentClass().createTarget(),new ParentClass().createTarget());
int i = 0;do {target = OthersClass.process(target);i++;} while (i < 3);
return target;}}
abstract class TargetClass {static final int STATIC_FIELD = 1;Object field;
TargetClass(TargetClass t1, TargetClass t2, TargetClass t3) {this.field = t1.field;}
class Child {Child(int value) {field = value;}}}
class ParentClass {TargetClass createTarget() {return new TargetClassImpl();}}
class TargetClassImpl extends TargetClass {TargetClassImpl() {super(null, null, null);}}
class OthersClass {private static TargetClass process(TargetClass target) {return super.createTarget(target);}
protected static TargetClass createTarget(TargetClass base) {return new TargetClassImpl();}}