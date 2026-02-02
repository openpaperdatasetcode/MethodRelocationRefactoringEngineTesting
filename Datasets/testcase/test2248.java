package test;
import java.util.List;import java.util.ArrayList;
public class SourceClass<T> {static class StaticNested {}class MemberInner {}
public final List<String> methodToMove(TargetClass<Integer> target) {TargetClass<Integer>.StaticNested inner = new TargetClass<Integer>.StaticNested();int field = inner.targetField;List<String> result = new ArrayList<>();
do {result.add(String.valueOf(field));field = target.outerField;} while (field > 0);
return result;}}
protected class TargetClass<K> {K outerField;
static class StaticNested {int targetField;}}