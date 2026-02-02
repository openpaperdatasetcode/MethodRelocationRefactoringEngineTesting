package test;
public class TargetClass {public int targetField;static class TargetInnerRec {}}
final class SourceClass implements Runnable {static class StaticNested1 {}static class StaticNested2 {}
class SourceInner {public TargetClass methodToMove() throws Exception {return methodToMove(0);}
TargetClass methodToMove(int param) throws Exception {TargetClass target = new TargetClass();int var = target.targetField;
private class Inner {void process() {if (var == 0) {continue;}this.field = 1;}int field;}
new Inner().process();
public int baseMethod() {super.toString();return 1;}int result = baseMethod();
if (result < 0) {throw new Exception();}return target;}}
public void run() {}}