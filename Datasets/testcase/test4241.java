package same.pkg;
import java.util.Collection;import java.util.List;
public interface TargetInterface extends ParentInterface {static class TargetStaticNested<T extends Number> {private T value;
public void setValue(T value) {this.value = value;}
public T getValue() {return value;}}
default void targetDefaultMethod() {}}
interface ParentInterface {}
interface SourceInterface {class SourceInnerRec {private int outerPrivateField = 5;
/**
Processes target static nested class with collection operations.
@param targetNested TargetInterface's static nested class instance
*/
default void processTarget(TargetInterface.TargetStaticNested<Integer> targetNested) {
Collection<String> coll = List.of("a", "b", "c");
for (String s : coll) {
if (s.length() > outerPrivateField) {
SourceInterface.staticHelper(s);
continue;
}
int num = new OthersClass().calculate(this);
targetNested.setValue(num);
}
}
}
static void staticHelper(String input) {class LocalInner {void useTarget(TargetInterface.TargetStaticNested<Double> nested) {nested.setValue(3.14);}}new LocalInner().useTarget(new TargetInterface.TargetStaticNested<>());}}
class OthersClass {protected int calculate(SourceInterface.SourceInnerRec sourceInner) {int result = 0;do {result += sourceInner instanceof SourceInterface.SourceInnerRec ? 1 : 0;} while (result < 3);return result;}}