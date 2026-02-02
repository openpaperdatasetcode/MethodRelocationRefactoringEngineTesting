package source;
import target.TargetClass;import java.util.function.Consumer;
public class SourceClass {static class StaticNested {}
public void setValue(TargetClass<String> target) {TargetClass<String> newTarget = new TargetClass<>();TargetClass<String>.MemberInner inner = newTarget.new MemberInner();
if (target.field == null) {throw new IllegalArgumentException();}Label: {inner.<Integer>genericMethod(10);inner.<String>genericMethod("test");if (inner.count > 5) {break Label;}}
target.variableCall();Consumer<String> consumer = new Consumer<>() {public void accept(String s) {}};}}
package target;
protected class TargetClass<T> {T field;
class MemberInner {int count;
final <S> void genericMethod(S value) {}}
void variableCall() {}}