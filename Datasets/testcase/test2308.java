package same.pkg;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
private enum Source {INSTANCE;
protected String outerProtectedField;
static class StaticNested {}
class MemberInner {}
@MyAnnotationstrictfp Target instanceMethod(Target<String> targetParam) {labeled: {synchronized (this) {Target.Inner inner = targetParam.new Inner();return inner.getTarget();}}
switch (targetParam.ordinal()) {case 0:List<String> list = new OthersClass().m1().m2().m3();break;}
variableCall(targetParam);outerProtectedField = "value";accessInstanceMethod();
return null;}
private void variableCall(Target<String> target) {Target<String> var = target;}
private void accessInstanceMethod() {new MemberInner();}
final List<String> callMethod() {return Source.INSTANCE.new MemberInner().instanceMethod(Target.INSTANCE)::getClass;}}
private enum Target<T> {INSTANCE;
T targetField;
class Inner {Target getTarget() {return Target.INSTANCE;}}
{Runnable anon = new Runnable() {@Overridepublic void run() {Target.INSTANCE.targetField = null;}};}}
class OthersClass {OthersClass m1() { return this; }OthersClass m2() { return this; }List<String> m3() { return new ArrayList<>(); }}