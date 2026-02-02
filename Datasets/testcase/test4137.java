package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;
public class SourceGeneric<T extends Comparable<T>> {public class SourceInner {public class SourceRecursiveInner {@Deprecatedpublic TargetGeneric<T> process(TargetGeneric<T> target) {class LocalInnerFirst {T getInnerData() {return target.targetField;}}
class LocalInnerSecond {boolean checkType(Object obj) {return obj instanceof TargetGeneric;}}
LocalInnerFirst firstInner = new LocalInnerFirst();LocalInnerSecond secondInner = new LocalInnerSecond();
T data = firstInner.getInnerData();boolean isTargetType = secondInner.checkType(target);
TargetGeneric<T> newTarget = new TargetGeneric<>(data);newTarget.processField = data.compareTo(target.targetField) > 0 ? data : target.targetField;
Supplier<List<String>> lambda = () -> TargetGeneric.staticM1().m2().m3();List<String> resultList = lambda.get();newTarget.resultSize = resultList.size();
return newTarget;}
public TargetGeneric<T> process(TargetGeneric<T> target, T extraParam) {TargetGeneric<T> newTarget = new TargetGeneric<>(extraParam);newTarget.processField = target.targetField;return newTarget;}}}}
class TargetGeneric<U extends Comparable> {
U targetField;
U processField;
int resultSize;
private TargetGeneric(U field) {this.targetField = field;}
public static <V extends Comparable<V>> TargetGeneric<V> staticM1() {return new TargetGeneric<V>(null) {public TargetGeneric<V> m2() {return this;}
public List<String> m3() {return new ArrayList<>(List.of("target_item"));}};}}