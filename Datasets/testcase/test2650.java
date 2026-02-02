package test.same;
import java.util.List;
protected abstract class SourceClass {static class StaticNested {}class MemberInner {record InnerRec(TargetClass target) {/**
Overloading method with generic features
*/
private int overloadingMethod() {
return 0;
}
private int overloadingMethod(TargetClass.Inner inner) {type DeclarationStatement: TargetClass.Inner.Generic<Integer> gen = inner.new Generic<>();
switch (inner.field) {case 1:gen.method(1);break;case 2:gen.method(2);break;default:gen.method(3);}
try {Object var = inner.field;var = gen.getValue();} catch (Exception e) {// No new exception thrown}
return (int) inner.field;}}}}
protected abstract class TargetClass {class Inner {int field;
class Generic<T> {private T value;
public void method(T param) {this.value = param;}
public T getValue() {return value;}}
Runnable anon = new Runnable() {@Overridepublic void run() {}};}}