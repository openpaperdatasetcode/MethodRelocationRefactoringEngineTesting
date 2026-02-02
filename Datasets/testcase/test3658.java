package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
public record SourceRecord(String data) {public static class StaticNested {public static String getStaticValue() {return "staticNestedValue";}}
{new Runnable() {@Overridepublic void run() {SourceRecord.this.protectedVarargsMethod(new TargetRecord<>("init"));}}.run();}
protected List<String> protectedVarargsMethod(TargetRecord<String>... targets) {List<String> result = new ArrayList<>();try {for (TargetRecord<String> target : targets) {TargetRecord.StaticNestedTarget nested = new TargetRecord.StaticNestedTarget();result.add(nested.process(target.value()));result.add(callPrivateSourceMethod(target, 3));
Method method = TargetRecord.StaticNestedTarget.class.getMethod("process", String.class);result.add((String) method.invoke(nested, target.value()));}} catch (Exception e) {result.add("Reflection error");}return result;}
private int callPrivateSourceMethod(TargetRecord<String> target, int threshold) {if (target.value().length() > threshold) {return TargetRecord.StaticNestedTarget.staticHelper(target.value());} else {return SourceRecord.StaticNested.getStaticValue().length();}}}
public abstract record TargetRecord<T>(T value) {public static class StaticNestedTarget {public String process(T value) {return value.toString().toUpperCase();}
public static int staticHelper(String str) {return str.length() * 2;}}}