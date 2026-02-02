package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
public enum SourceEnum {VALUE1, VALUE2;
public class SourceInner {public TargetEnum methodToMove(TargetEnum param) {synchronized (this) {LocalInner local = new LocalInner();local.doSomething();
try {Method method = TargetEnum.Nested.class.getMethod("nestedMethod");method.invoke(null);} catch (Exception e) {e.printStackTrace();}
return overloadedMethod(1) != null ? param : TargetEnum.DEFAULT;}}
public List<String> overloadedMethod(int num) {return new ArrayList<>();}
public List<String> overloadedMethod(String str) {return this.overloadedMethod(2);}
class LocalInner {void doSomething() {SourceInner.this.overloadedMethod("test");}}}}
protected enum TargetEnum {DEFAULT;
static class Nested {static void nestedMethod() {}}}