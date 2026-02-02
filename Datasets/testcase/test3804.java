package samepkg;
class Outer {protected int outerProtected;}
private class SourceClass extends Outer {class MemberInner {}
private Object varargsMethod(@Deprecated String... args) {super();TargetClass.TargetStaticNested nested = new TargetClass.TargetStaticNested();outerProtected = 5;List rawList = new ArrayList();MemberInner inner = new MemberInner();return targetField;}
public String callMethod() {return "called";}
void methodWithAnonymous() {Runnable r = new Runnable() {public void run() {try {String str = (param) -> callMethod();} catch (Exception e) {}}};}
TargetClass.TargetStaticNested targetField;}
final class TargetClass {static class TargetStaticNested {}}