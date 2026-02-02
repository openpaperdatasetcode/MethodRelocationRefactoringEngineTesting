package test;
import java.util.function.Consumer;
strictfp class SourceClass {class MemberInner1 {}class MemberInner2 {}
@MyAnnotationTargetClass getTarget(TargetClass target) {OthersClass others = new OthersClass();Consumer<Void> consumer = others::setField;
{others.this.calculate(2);}
TargetClass newTarget = new TargetClass(target.super.field + 1);TargetClass.Inner inner = newTarget.new Inner();
loop: {inner.setValue(others.getField());if (inner.getValue() > 5) {break loop;}}
return newTarget;}
// Overloaded accessor methodTargetClass getTarget(String param) {return new TargetClass(0);}}
final class TargetClass extends SuperClass {class Inner {private int value;
public int getValue() {return value;}
public void setValue(int value) {this.value = value;}}
private TargetClass(int field) {super.field = field;}}
class SuperClass {int field;}
class OthersClass {private int field;
public int getField() {return field;}
public void setField(Void v) {this.field = 2;}
strictfp int calculate(int num) {return num * 2;}}
@interface MyAnnotation {}
