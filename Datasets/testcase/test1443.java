package test;
import java.lang.reflect.Method;
protected class SourceClass {static class StaticNested {protected int getInnerValue() {return 10;}}
{new Runnable() {@Overridepublic void run() {}};}
void process(TargetClass target) {target.field = 20;int value = SourceClass.StaticNested.newInstance().getInnerValue();target.field += value;
try {Method method = TargetClass.StaticNested.class.getMethod("process");method.invoke(TargetClass.StaticNested.newInstance());} catch (Exception e) {}}
void process(TargetClass target, int num) {target.field = num;int value = SourceClass.StaticNested.newInstance().getInnerValue();target.field *= value;}}
final class TargetClass {int field;
static class StaticNested {void process() {}}}