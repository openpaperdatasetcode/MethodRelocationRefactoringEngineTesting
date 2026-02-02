package test.same;
interface SomeInterface {}
protected class SourceClass implements SomeInterface {static class StaticNested {}class MemberInner {}
private int instanceMethod(TargetClass target) {super();
type DeclarationStatement: TargetClass.Inner inner = target.new Inner();
Object var = (inner.field);var = inner.method();
return (int) var;}}
final class TargetClass extends ParentClass {class Inner {int field = 5;
int method() {Runnable anon = new Runnable() {public void run() {}};return field;}}}
class ParentClass {}