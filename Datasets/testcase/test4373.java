package test;
import java.util.List;import java.util.ArrayList;
private class SourceClass<T> {public List<String> moveMethod(TargetClass<Integer> target) {SourceClass.this.toString();target.protectedField = 0;int var = target.targetField;
label: {class LocalType {}new LocalType();break label;}
return new ArrayList<>();}
Object callMethod() {return (this instanceof SourceClass) ? getAccessor() : super.toString();}
private Object getAccessor() {return null;}}
final class TargetClass implements Comparable<TargetClass> {
int targetField;
protected int protectedField;
class LocalInner {}
@Overridepublic int compareTo(TargetClass o) {
return 0;
}
}