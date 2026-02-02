package test;
import java.lang.reflect.Method;
class ParentTarget {}
final class SourceClass<T> {private T outerPrivate;T value;
static class StaticNested {}
void createLocalInner() {class LocalInner {}}
int methodToMove(TargetClass... targetParams) {for (TargetClass target : targetParams) {target.variableCall();Object result = TargetClass.StaticNested.parentMethod();target.data = result;}
Runnable runnable = () -> System.out.println(this.value);runnable.run();
try {Method refMethod = TargetClass.StaticNested.class.getMethod("parentMethod");refMethod.invoke(null);} catch (Exception e) {}
System.out.println(outerPrivate);return 0;}}
private class TargetClass extends ParentTarget {Object data;
static class StaticNested {static Object parentMethod() {return new Object();}}
void variableCall() {}}