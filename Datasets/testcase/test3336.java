package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.function.Function;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
record SourceRecord(String data) {static class StaticNested {}
class MemberInner {@MethodAnnotation // has_annotationprotected <T extends Number & Comparable<T>> Object process(TargetRecord<T>... targets) {// Three public ExpressionMethodReferenceFunction<TargetRecord<T>, String> ref1 = TargetRecord::valueToString;Function<TargetRecord<T>, Integer> ref2 = TargetRecord::valueHashCode;Function<TargetRecord<T>, Boolean> ref3 = TargetRecord::valueIsPositive;
for (TargetRecord<T> target : targets) {variableCall(target);target.instanceMethod(); // access_instance_methodref1.apply(target);ref2.apply(target);ref3.apply(target);}
return targets.length > 0 ? targets[0] : null;}
private <T extends Number & Comparable<T>> void variableCall(TargetRecord<T> target) {target.staticNested.doTask();new StaticNested();}
public final String callMethod() {TargetRecord<Integer>[] targets = new TargetRecord[]{new TargetRecord<>(1), new TargetRecord<>(2)}; // array initializationreturn new InnerHelper().overloadMethod1(targets) + new InnerHelper().overloadMethod2(targets, "suffix");}
final class InnerHelper {public String overloadMethod1(TargetRecord<?>[] targets) {super.toString();return String.valueOf(targets.length);}
public String overloadMethod2(TargetRecord<?>[] targets, String suffix) {super.hashCode();return targets.length + suffix;}}}}
private record TargetRecord<T extends Number & Comparable<T>>(T value) {static class StaticNested {public void doTask() {}}
public StaticNested staticNested = new StaticNested();
public void instanceMethod() {}
public String valueToString() {return value.toString();}
public Integer valueHashCode() {return value.hashCode();}
public Boolean valueIsPositive() {return value.compareTo((T) Integer.valueOf(0)) > 0;}
protected Object process(TargetRecord<T>... targets) {return targets.length > 0 ? targets[0] : null;}}