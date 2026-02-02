package test;
import java.util.List;import java.util.ArrayList;
public class SourceClass<T> {class MemberInner {}
protected List<String> methodToMove(TargetClass<String>.StaticNested inner) {// Local inner classclass LocalInner {}new LocalInner();
// Super constructor invocation (implicit for inner class)super.toString();
// Super keywordsString superStr = super.getClass().getSimpleName();
// Access target fieldList<String> targetField = inner.nestedField;
// Variable callList<String> result = new ArrayList<>(targetField);result.add(superStr);
return result;}}
class TargetClass<V> {static class StaticNested {List<V> nestedField;}}