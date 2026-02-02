package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Consumer;
record SourceRecord<T>(T content) {static class StaticNested {public static void process(U value) {
System.out.println("Static processing: " + value);
}
}
class MemberInner {public void handle(TargetRecord<?> target) {target.process();}}
final TargetRecord<T> method(T... items) {// Constructor invocation of target recordTargetRecord<T> target = new TargetRecord<>(new ArrayList<>());
// Abstract method call in for loop (implemented by target's local inner class)for (int i = 0; i < 1; i++) {target.getProcessor().processItem(items[i]);}
// PrefixExpression with number 1 and protected modifierprotected int index = 0;while (index < items.length) {target.values().add(items[++index]);}
// Variable call to target's componenttarget.values().add(content);
// Collection operation with method reference to source classtarget.values().forEach(SourceRecord::processItem);
return target;}
public static <T> void processItem(T item) {System.out.println("Processing item: " + item);}}
public record TargetRecord<S>(List<S> values) implements Processor<S> {@Overridepublic Processor<S> getProcessor() {// Local inner class implementing abstract methodclass LocalProcessor implements Processor<S> {@Overridepublic void processItem(S item) {values.add(item);}}return new LocalProcessor();}
// Anonymous inner class usagepublic Runnable getRunnable() {return new Runnable() {@Overridepublic void run() {System.out.println("Processing target: " + values);}};}}
interface Processor<T> {void processItem(T item);Processor<T> getProcessor();}