package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
private class SourceClass {public static class StaticNested {}
class MemberInner {class SourceInnerRec {/**
Processes target with varargs and interacts with static nested class
@param target Final target class
@param args Variable arguments
@return List of processed strings*/public List<String> moveMethod(FinalTarget target, String... args) {List<String> result = new ArrayList<>();for (int i = 0; i < args.length; i++) {try {if (args[i].isEmpty()) {continue;}String processed = target.staticNested.process(args[i], FinalTarget.STATIC_FIELD);result.add(processed);} catch (Exception e) {e.printStackTrace();}}
Supplier<String> supplier = () -> target.staticNested.m1().m2().m3();result.add(supplier.get());
try {Method method = FinalTarget.StaticNested.class.getMethod("process", String.class, int.class);method.invoke(target.staticNested, "reflection", FinalTarget.STATIC_FIELD);} catch (Exception e) {}
return result;}}}}
final class FinalTarget {public static final int STATIC_FIELD = 100;public static final StaticNested staticNested = new StaticNested();
public static class StaticNested {public String process(String input, int field) {return input + field;}
public StaticNested m1() { return this; }public StaticNested m2() { return this; }public String m3() { return "chained"; }}}
