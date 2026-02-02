package test.refactoring.movemethod;
import java.util.function.Consumer;
protected class SourceClass<T extends CharSequence> extends SuperSourceClass<T> {static class StaticNestedSource {
public void log(U data) {
System.out.println("Nested log: " + data);
}
}
class MemberInnerSource {@SuppressWarnings("unchecked")public TargetClass<T> processVarargs(TargetClass<T> targetParam, T... items) {class SourceInnerRec {TargetClass<T> handle() {super.logPrefix("Processing varargs");
switch (items.length) {case 0:targetParam.process(() -> System.out.println("No items"));break;case 1:StaticNestedSource<T> nested = new StaticNestedSource<>();nested.log(items[0]);targetParam.process(() -> System.out.println("Single item: " + items[0]));break;default:targetParam.process(() -> System.out.println("Multiple items: " + items.length));}
Consumer<T> consumer = targetParam::acceptItem;for (T item : items) {consumer.accept(item);}
return targetParam;}}
return new SourceInnerRec().handle();}}}
abstract class SuperSourceClass<T> {protected void logPrefix(String prefix) {System.out.println(prefix + ": " + getClass().getSimpleName());}}
private class TargetClass<T extends CharSequence> {public void process(Runnable task) {task.run();}
public void acceptItem(T item) {System.out.println("Accepted: " + item);}}
public class MoveMethodTest5189 {public static void main(String[] args) {SourceClass<String> source = new SourceClass<>();TargetClass<String> target = new TargetClass<>();SourceClass<String>.MemberInnerSource inner = source.new MemberInnerSource();
TargetClass<String> result = inner.processVarargs(target, "arg1", "arg2", "arg3");System.out.println("Result class: " + result.getClass().getSimpleName());}}