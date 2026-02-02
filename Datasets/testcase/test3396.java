package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Function;
sealed class SourceClass<T> permits SourceSubclass<T> {private TargetClass<T> targetField = new TargetClass<>();
public class MemberInner {public void innerMethod() {}}
public static class StaticNested {}
{TargetClass<T>.StaticNested<T> targetNested = targetField.new StaticNested<>();Function<TargetClass<T>, List<String>> func1 = targetNested::overloadedMethod;Function<TargetClass<T>, List<String>> func2 = targetNested::overloadedMethod;}
protected Object instanceMethod() throws IllegalStateException {labeledLoop:for (int i = 0; i < 5; i++) {if (i % 2 == 0) {continue labeledLoop;}targetField.variableCall();this.new MemberInner().innerMethod();}
if (targetField == null) {throw new IllegalStateException("Target field cannot be null");}
TargetClass<T>.StaticNested<T> nested = targetField.new StaticNested<>();nested.process(SourceClass.this.targetField);
return nested;}}
final class SourceSubclass<T> extends SourceClass<T> {}
public class TargetClass {
public static class StaticNested<V> {
public List<String> overloadedMethod() {
return new ArrayList<>();
}
public List<String> overloadedMethod(String param) {return new ArrayList<>(List.of(param));}
public void process(TargetClass target) {}
}
public void variableCall() {}}