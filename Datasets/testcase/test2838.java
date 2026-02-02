package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface TestAnnotation {}
public record SourceClass<T>(T data) {static class StaticNested {int value;void process() {}}
@TestAnnotationprivate List<String> methodToMove(TargetClass targetParam) {List<String> result = new ArrayList<>();int count = 0;
do {count++;targetParam.nested().innerRec().add(result, "item" + count);} while (count < 2);
while (count > 0) {count--;StaticNested sn = new StaticNested();sn.process();}
Object genericObj = createGeneric(targetParam, 2, String::new);
Runnable r = () -> {new Object() {{targetParam.nested().innerRec().print();}};};r.run();
return result;}
public <U, V> Object createGeneric(TargetClass target, int num, V supplier) {return target.nested().innerRec();}}
/**
Javadoc for TargetClass record
*/
strictfp record TargetClass(Nested nested) {
record Nested(InnerRec innerRec) {
record InnerRec() {
void add(List<String> list, String item) {
list.add(item);
}
void print() {}
}
}
}