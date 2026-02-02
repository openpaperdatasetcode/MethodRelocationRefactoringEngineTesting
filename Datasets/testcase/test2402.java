package test.refactoring.movemethod;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.function.Consumer;
@Retention(RetentionPolicy.RUNTIME)@interface CustomAnnot {}
private record TargetClass(String value) {static class TargetInner {record NestedInner(int num) {}}}
sealed record SourceClass(String name) implements Runnable permits SourceSubClass {@CustomAnnotpublic final void process(TargetClass... targets) {if (targets.length == 0) {return;}
class LocalInner {<T> void handle(T item) {System.out.println(item);}}LocalInner local = new LocalInner();Object varCall = local;
for (TargetClass target : targets) {expression:varCall = target.value();local.handle(target.value());}
TargetClass.TargetInner.NestedInner nested = new TargetClass.TargetInner.NestedInner(1);switch (nested.num()) {case 1:Consumer<TargetClass.TargetInner.NestedInner> consumer = (n) -> local.handle(n.num());consumer.accept(nested);break;default:break;}}
public final void process(Integer num) {}
@Overridepublic void run() {new Runnable() {@Overridepublic void run() {process(new TargetClass("test"));}}.run();}}
non-sealed record SourceSubClass(String name) extends SourceClass {public SourceSubClass(String name) {super(name);}}
import org.junit.Test;
public class MoveMethodTest3063 {@Testpublic void testVarargsMethod() {SourceClass source = new SourceSubClass("source");source.process(new TargetClass("a"), new TargetClass("b"));}}