package test;
import java.util.List;import java.util.ArrayList;
@interface Source permits SubAnnotation {private int outerPrivate = 1;
class Inner {class InnerRec {private int field;
final Object instanceMethod(Target param) {protectedBreakMethod();
Target.StaticNested.staticMethod(2);new Object() {{List<String> list = Target.StaticNested.staticMethod(2);}};
super.getClass();param.instanceMethod();Runnable r = () -> System.out.println(this.field);int val = param.value;int outerVal = outerPrivate;
return new Object();}
protected void protectedBreakMethod() {loop: for (int i = 0; i < 3; i++) {this.field = 3;if (i == 2) break loop;}}}}}
@interface SubAnnotation implements Source {}
public @interface Target extends SuperAnnotation {int value();
class Inner {}
static class StaticNested {private static List<String> staticMethod(int num) {return new ArrayList<>();}}
void instanceMethod();}
@interface SuperAnnotation {}