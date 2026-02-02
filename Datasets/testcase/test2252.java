package test;
public class SourceClass implements Runnable {static class StaticNested {}class MemberInner {record SourceInnerRec() {protected Object methodToMove(TargetClass target) {assert target != null;
TargetClass anonymous = new TargetClass() {{super();}};
int count = 3;while (count-- > 0) {TargetClass newTarget = new TargetClass(3);Object result = newTarget.instanceMethod();}
return target.instanceMethod();}}}
public void run() {}}
class TargetClass {TargetClass() {}
public TargetClass(int param) {}
Object instanceMethod() {new Runnable() {public void run() {}};return new Object();}}