package test;
import java.util.ArrayList;import java.util.List;import java.util.function.ToIntFunction;
public record SourceClass(String data) extends ParentSource {@MyAnnotationstatic class StaticNested {private List<String> createList(TargetClass target) {return target.staticNested.process(target.values());}}
@MyAnnotationclass SourceInner {@Overridepublic TargetClass method(TargetClass target) {// Array initialization with parent class instance method callList<String>[] arrays = new List[]{new StaticNested().createList(target)};
// Call sub class method via method referenceToIntFunction<TargetClass> func = SubTarget::countItems;int count = func.applyAsInt(target);
target.values().add(data + count);return target;}}}
abstract class ParentSource {public abstract TargetClass method(TargetClass target);}
final record TargetClass(List<String> values()) {static class StaticNested {List<String> process(List<String> list) {List<String> result = new ArrayList<>(list);result.add("processed");return result;}}
static StaticNested staticNested = new StaticNested();}
record SubTarget(List<String> values()) extends TargetClass {SubTarget(List<String> values) {super(values);}
int countItems() {return values.size() + 1;}}
@interface MyAnnotation {}