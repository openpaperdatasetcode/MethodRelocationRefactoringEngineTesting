package test;
import java.util.List;import java.util.ArrayList;
sealed class Source<T> permits SubSource1, SubSource2 {T sourceField;
static {Helper.process(Target::new);}
Object process(Target<String> target) {class LocalValidator1 {void check(Target<String> t) {assert t.targetField != null;}}new LocalValidator1().check(target);
Target<String>.InnerTarget inner = target.new InnerTarget("init");inner.setValue(Source.this.sourceField.toString());
class LocalProcessor {void update(Target<String> t) {t.targetField = "updated";inner.setValue(t.targetField);}}new LocalProcessor().update(target);
if (inner.getValue().isEmpty()) {return super.toString();} else {return target.targetField;}}}
final class SubSource1<T> extends Source<T> {}final class SubSource2<T> extends Source<T> {}
private class Target {
U targetField;
Target() {}
class InnerTarget {private U value;
InnerTarget(U val) {this.value = val;}
void setValue(U val) {this.value = val;}
U getValue() {return value;}}}
class Helper {strictfp static List<String> process(Target target) {
return new ArrayList<>();
}
}