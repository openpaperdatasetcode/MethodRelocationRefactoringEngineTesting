package test;
import java.lang.reflect.Method;import java.util.function.Runnable;
class SourceClass<T> {static class StaticNested {}
class InnerClass {protected Object methodToMove(TargetClass<T>... targets) {super();SourceClass.this.toString();
for (TargetClass<T> target : targets) {target.variableCall();String expr = target.data.toString();}
Runnable r = new Runnable() {public void run() {}};
try {Method method = TargetClass.class.getMethod("variableCall");method.invoke(targets[0]);} catch (Exception e) {}
return null;}}}
public class TargetClass<T> {T data;
void variableCall() {class LocalInner {}}}