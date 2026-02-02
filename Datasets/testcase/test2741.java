package test.same;
import java.lang.reflect.Method;import java.util.function.Consumer;
protected class SourceClass {static class StaticNested {}
class InnerClass {/**
Method Javadoc*/protected TargetClass getTarget() {super.toString();TargetClass target = new TargetClass();TargetClass.StaticNested nested = target.new StaticNested();Object var = nested.targetField;
if (var == null) {throw new NullPointerException();}
Consumer<Integer> lambda = (i) -> {if (i > 1) {super.getTarget();}};lambda.accept(1);
try {Method method = TargetClass.StaticNested.class.getMethod("toString");method.invoke(nested);} catch (Exception e) {}
return target;}}
Runnable anon = new Runnable() {public void run() {}};}
private class TargetClass {class StaticNested {protected Object targetField;}}