package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Consumer;
sealed class SourceClass<S> permits SourceSubClass {protected S outerProtectedField;private int sourceField;
public class MemberInner {}
void sampleLocalClass() {class SourceLocalInner {}SourceLocalInner local = new SourceLocalInner();}
protected List<String> instanceMethod(TargetClass<Integer> target) {List<String> result = new ArrayList<>();TargetClass<Integer> targetInstance = new TargetClass<>();
public int threshold = 3;if (target.targetField > threshold) {this.sourceField = target.targetField;result.add(String.valueOf(this.sourceField));}
protected Consumer<String> lambda = (str) -> {result.add(str + outerProtectedField);};lambda.accept("Value: ");
return result;}}
final class SourceSubClass<S> extends SourceClass<S> {}
final class TargetClass<T> {T targetField;
void targetLocalClass() {class TargetLocalInner {}TargetLocalInner local = new TargetLocalInner();}}