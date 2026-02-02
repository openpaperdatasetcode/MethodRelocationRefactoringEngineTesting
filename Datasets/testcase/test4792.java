package test;
import java.lang.reflect.Method;
class BaseGeneric<T> {private void process(T target) {}}
protected class SourceClass<S> extends BaseGeneric<TargetClass<S>.Inner> {@Overrideprivate void process(TargetClass<S>.Inner target) {for (int i = 0; i < 5; i++) {target.doSomething();}
try {Method method = TargetClass.class.getMethod("createInner");method.invoke(new TargetClass<S>());} catch (Exception e) {}}}
public class TargetClass<T> {public class Inner {public void doSomething() {}}
public Inner createInner() {class LocalInner {}return new Inner();}}