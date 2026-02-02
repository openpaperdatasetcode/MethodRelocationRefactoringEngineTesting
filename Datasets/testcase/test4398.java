package test;
import java.util.function.Function;
non-sealed record TargetClass<T>(T data) {class TargetMemberInner {class TargetRecursiveInner {void updateData(T value) {System.out.println(TargetClass.this.data() + "_" + value);}}}}
class OtherClass {private void otherMethod(TargetClass<?> target) {throw new IllegalArgumentException("Processing target: " + target.data());}}
public record SourceClass(String sourceField) {private void normalMethod(TargetClass<String> target) {TargetClass.TargetMemberInner inner = target.new TargetMemberInner();TargetClass.TargetMemberInner.TargetRecursiveInner recInner = inner.new TargetRecursiveInner();OtherClass other = new OtherClass();
class LocalInner {<A, B, C> TargetClass<String> genericMethod(A a, B b, C c) {return new TargetClass<>((String) a + b + c);}}
LocalInner local = new LocalInner();Function<Object[], TargetClass<String>> methodRef = local::genericMethod;
if (target.data() != null) {TargetClass<String> newTarget = methodRef.apply(new Object[]{"a", "b", "c"});variableCall(newTarget, recInner);} else {try {new OtherClass().otherMethod(target);} catch (IllegalArgumentException e) {// Handle exception}}
Runnable anonymous = new Runnable() {@Overridepublic void run() {recInner.updateData(target.data() + "_anonymous");}};anonymous.run();}
private void variableCall(TargetClass<String> target, TargetClass.TargetMemberInner.TargetRecursiveInner recInner) {recInner.updateData(target.data());}}