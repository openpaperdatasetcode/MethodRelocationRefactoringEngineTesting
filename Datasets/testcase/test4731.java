package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.function.Runnable;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {String value() default "";}
class Parent {int superField;}
final class SourceClass {private int outerPrivate = 5;
class MemberInner {final int moveMethod(TargetClass.Inner target, int n) {class LocalInner {}LocalInner li = new LocalInner();
private Parent parent = new Parent();parent.superField = 1;
expressionStatement();variableCall(target);
for (int i = 0; i < n; i++) {Runnable runnable = () -> System.out.println(this.outerPrivate);runnable.run();}
return (n <= 0) ? target.field : moveMethod(target, n - 1);}
private void expressionStatement() {}
private void variableCall(TargetClass.Inner inner) {}}}
public class TargetClass extends Parent {class Inner {int field;
void setField(int val) {this.field = val;}}
void method() {class LocalInner {}}}
class SubClass extends TargetClass {@MyAnnotation(value = "")final void callMethod() {super.method();new Inner().setField(10);}}