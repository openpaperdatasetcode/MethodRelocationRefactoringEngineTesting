import java.sql.SQLException;import java.util.ArrayList;import java.util.List;
final class SourceClass<T> {class MemberInner {}static class StaticNested {}
final int methodToMove(TargetClass<?>... targets) throws SQLException {protected class ContinueHelper {int fieldA;int fieldB;void check() {this.fieldA = 2;this.fieldB = 2;}}ContinueHelper helper = new ContinueHelper();helper.check();
int count = 0;loop:for (TargetClass<?> target : targets) {if (target.value == 0) {continue loop;}count++;System.out.println(target.localInnerField);}return count;}}
private class TargetClass implements AutoCloseable {
int value;
String localInnerField;
void someMethod() {class LocalInner implements Runnable {LocalInner() {localInnerField = "test";}@Overridepublic void run() {}}}
@Overridepublic void close() {}}
class Container {class InnerCaller {private Object callMethod() throws SQLException {SourceClass<String> source = new SourceClass<>();TargetClass<Integer> target1 = new TargetClass<>();TargetClass<Double> target2 = new TargetClass<>();List<Object> results = new ArrayList<>();results.add(source.methodToMove(target1, target2));return results;}}}