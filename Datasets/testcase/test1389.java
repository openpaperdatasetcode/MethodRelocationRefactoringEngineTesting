package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
sealed record SourceRecord<T>(T data) permits ExtendedSourceRecord {private static class StaticNested1 {}private static class StaticNested2 {}
class SourceInner {class SourceInnerRec { // source_inner_rec@Overrideprotected List<String> methodToMove(TargetRecord.TargetParam... params) { // contains target parameter (per_condition)// super constructor invocationStaticNested1 nested1 = new StaticNested1() {};
// numbers:1, modifier:public, exp:CastExpressionObject obj = 1;int castVal = (int) obj;
// variable callStaticNested2 nested2 = new StaticNested2();SourceRecord.this.data(); // access_instance_method
// functional interfaces with instance methodRunnable runnable = () -> {TargetRecord target = new TargetRecord("test");TargetRecord result = target.instanceMethod(1, SourceRecord.this); // 1, source, instance, instanceReference.methodName(arguments)};
// requires_try_catch (reflection)try {Method method = TargetRecord.MemberInner.class.getMethod("innerMethod");} catch (Exception e) {} // used_by_reflection
// call_method: source, private, constructor, lambda, pos:constructor parameter listSourceRecord<String> record = new SourceRecord<>("data", (a) -> a.length());
return new ArrayList<>();}}}
// call_method target: private constructor with lambda parameterprivate SourceRecord(T data, java.util.function.Function<T, Integer> func) {this(data);func.apply(data);}}
non-sealed record ExtendedSourceRecord<T>(T data) extends SourceRecord<T> {}
record TargetRecord(String value) {static class TargetParam {}
class MemberInner { // target_innerpublic void innerMethod() {}}
// anonymous inner class (target_feature)Runnable anonymous = new Runnable() {@Overridepublic void run() {}};
// instance method (feature target)public final TargetRecord instanceMethod(int num, SourceRecord<?> source) { // final modifierreturn new TargetRecord("result");}}