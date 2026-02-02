package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
public class SourceClass {class SourceInner {/**
Javadoc for targetMethod*/@Deprecatedint targetMethod(TargetClass target) {super.getClass();List<String> list = new ArrayList<>();for (String s : list) {}
int num = switch (target.getValue()) {case 1 -> 10;default -> 20;};
try {Method method = TargetClass.class.getMethod("protectedMethod");method.invoke(target);} catch (Exception e) {}
return num;}}
void anonymousClass() {Runnable r = new Runnable() {public void run() {}};}}
class TargetClass {private int value;
TargetClass() {Runnable r = new Runnable() {public void run() {}};}
protected List<String> protectedMethod() {return new ArrayList<>();}
int getValue() {return value;}
void doWhileCall() {SourceClass outer = new SourceClass();SourceClass.SourceInner inner = outer.new SourceInner();do {inner.targetMethod(this);} while (false);}}