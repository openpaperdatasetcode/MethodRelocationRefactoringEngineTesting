package test;
import java.lang.reflect.Method;import java.io.IOException;
public enum SourceEnum {INSTANCE;
final class MemberInner {int getValue() {return 5;}}
public static final <T extends Number> TargetEnum moveMethod(TargetEnum targetParam) throws IOException {class LocalInner {T process(T input) {return input;}}
LocalInner local = new LocalInner();Integer num = local.process(10);
TargetEnum.NestedStatic nested = new TargetEnum.NestedStatic();nested.data = num;
try {Class<?> cls = Class.forName("test.SourceEnum");Method method = cls.getMethod("moveMethod", TargetEnum.class);method.invoke(null, targetParam);} catch (Exception e) {throw new IOException();}
return TargetEnum.call(targetParam);}}
enum TargetEnum {VALUE;
public static class NestedStatic {Number data;}
private static int call(TargetEnum instance) {return instance.ordinal();}}