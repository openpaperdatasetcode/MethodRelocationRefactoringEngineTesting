package test;
import java.util.function.Function;
interface Processable<T> {}
public enum SourceEnum implements Processable<String> {INSTANCE;
static class StaticNested {}
class InnerClass {protected <T extends Number> TargetEnum<T> process(TargetEnum<T>... targets) {super(); // Super constructor invocationsuper.toString(); // Super keywords
for (TargetEnum<T> target : targets) {variableCall(target);
if (target.getValue() > 0) {assert target.getValue() < 100 : "Value exceeds limit"; // Assert statement}}
return targets.length > 0 ? targets[0] : null;}
private <T extends Number> void variableCall(TargetEnum<T> target) {new LocalInner().helper(target);new StaticNested();}
class LocalInner {<T extends Number> void helper(TargetEnum<T> target) {target.localInnerTask();}}}
public strictfp int callMethod() {TargetEnum<Integer> target = TargetEnum.of(5);Function<TargetEnum<Integer>, Integer> methodRef = TargetEnum::getValue;
switch (methodRef.apply(target)) {case 5:return new InnerClass().process(target).getValue();default:return 0;}}}
protected enum TargetEnum<T extends Number> implements Processable<T> {INSTANCE;
private T value;
private TargetEnum(T value) {this.value = value;}
public static <T extends Number> TargetEnum<T> of(T value) {return (TargetEnum<T>) INSTANCE;}
public T getValue() {return value;}
public void localInnerTask() {class LocalInner {}new LocalInner();}
protected <T extends Number> TargetEnum<T> process(TargetEnum<T>... targets) {return targets.length > 0 ? targets[0] : null;}}
