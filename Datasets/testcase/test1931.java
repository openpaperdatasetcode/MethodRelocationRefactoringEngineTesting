package test;
public class SourceClass {@MyAnnotationstatic class StaticNested {void useTargetInner(TargetClass target) {target.new Inner().setValue(5);}}
class MemberInner {void invokeInnerMethod(TargetClass target) {target.new Inner().printField();}}
@MyAnnotationfinal void method(TargetClass target) {boolean condition = target.field > 0;condition ? target.new Inner().increment() : target.new Inner().decrement();
new StaticNested().useTargetInner(target);new MemberInner().invokeInnerMethod(target);}
final void method(TargetClass target, int value) {target.field = value;target.StaticNested.process(target);}}
non-sealed class TargetClass {int field;
class Inner {protected void setValue(int val) {field = val;}
protected void printField() {System.out.println(field);}
protected void increment() {field++;}
protected void decrement() {field--;}}
static class StaticNested {static void process(TargetClass target) {target.field *= 2;}}}
@interface MyAnnotation {}