package test;
import java.lang.reflect.Method;
public class SourceClass<T> {static class StaticNested {}
class SourceInner {class SourceInnerRec {public TargetClass.TargetInner.TargetInnerRec moveMethod(TargetClass param) {loop: for (int i = 0; i < 5; i++) {if (i % 2 == 0) {continue loop;}param.value++;}
private TargetClass.TargetInner.TargetInnerRec innerRec = new TargetClass.TargetInner.TargetInnerRec(TargetClass.staticField);TargetClass.TargetInner inner = param.new TargetInner();innerRec = inner.new TargetInnerRec(0);innerRec.action();
try {Method method = SourceInnerRec.class.getMethod("moveMethod", TargetClass.class);method.invoke(this, param);} catch (Exception e) {}
return innerRec;}}}}
public class TargetClass {public int value;static int staticField = 10;
class TargetInner {class TargetInnerRec {public TargetInnerRec(int val) {}
void action() {}
@Overridepublic String toString() {return "";}}}}
class OthersClass {protected String callMethod() {SourceClass<String> source = new SourceClass<>();TargetClass target = new TargetClass();Runnable r = () -> {TargetClass.TargetInner.TargetInnerRec rec = target.new TargetInner().new TargetInnerRec(5);rec.action();};return "";}}