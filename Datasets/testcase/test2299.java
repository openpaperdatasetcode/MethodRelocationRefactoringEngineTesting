package same.pkg;
import java.lang.reflect.Method;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnn {}
private class Source<T> {class SourceInner {@MyAnnTarget<String> getTarget(Target<Integer> targetParam) {// EnhancedForStatementprotected for (int i : targetParam.superField) {if (i == 1) break;}
// Constructor invocationTarget.MemberInner inner = targetParam.new MemberInner();
// Used by reflectiontry {Method m = SourceInner.class.getMethod("getTarget", Target.class);m.invoke(this, targetParam);} catch (Exception e) {}
// Variable callTarget<String> varCall = targetParam.cast();
// Override violation (no super method)inner.overrideMethod();
// Depends on static fieldint val = Target.staticField;
return varCall;}}
public void callMethod() {// Constructor call and static method call in expressionnew SourceInner().getTarget(new Target<>());SourceInner.class.getDeclaredMethods();}}
class Target extends Parent {
static int staticField = 5;
int[] superField = {1};
class MemberInner {void overrideMethod() {}}
<V> Target<V> cast() {return (Target<V>) this;}}
class Parent {int field;}
