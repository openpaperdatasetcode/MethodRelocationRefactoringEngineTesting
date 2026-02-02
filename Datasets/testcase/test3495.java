package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.function.Function;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {String value();}
abstract class ParentGenericClass<T> {protected abstract Object moveMethod(TargetGenericClass<T> param);}
abstract class SourceGenericClass<T> extends ParentGenericClass<T> {static int staticField = 3;
class MemberInner {void useAnonymous() {new Runnable() {@Overridepublic void run() {SourceGenericClass.this.moveMethod(new TargetGenericClass<>());}}.run();}}
@MyAnnotation(value = "lambda: (a,b,c) -> a + b + c")@Overrideprotected Object moveMethod(TargetGenericClass<T> param) {Function<Integer, Integer> func = (a) -> a + staticField;OtherClass.process(this);variableCall(param);return param.getData();}
private void variableCall(TargetGenericClass<T> target) {target.useStaticField(staticField);}}
abstract class TargetGenericClass<T> {T getData() {return null;}
void useStaticField(int field) {}
@Overrideprotected Object moveMethod(TargetGenericClass<T> param) {return null;}}
class OtherClass {static <T> void process(SourceGenericClass<T> source) {}}
class SubClass<T> extends SourceGenericClass<T> {private abstract int abstractMethod(int a, int b, int c);}
