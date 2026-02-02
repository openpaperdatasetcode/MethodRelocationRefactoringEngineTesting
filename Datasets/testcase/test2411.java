package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface CustomAnnot {}
interface DataHandler {List<String> handle();}
protected class TargetClass implements DataHandler {public int count = 0;private String data;
public TargetClass(String data) {this.data = data;new Runnable() {@Overridepublic void run() {count++;}}.run();}
private int increment() {return ++count;}
@Overridepublic List<String> handle() {return new ArrayList<>();}}
abstract class OtherClass {public abstract synchronized int process(TargetClass target);}
private class SourceClass extends OtherClass {static class StaticNested1 {}static class StaticNested2 {}
@CustomAnnot@Overrideprotected List<String> handle() {TargetClass target = new TargetClass("test");Object varCall = target.count;
// Type declaration statementclass LocalType {String getInfo(TargetClass t) {return t.data;}}LocalType local = new LocalType();
// IfStatement featureif (target.count > 3) {varCall = target.data;}
// Expression statementtarget.count = 2;
// Do statementdo {target.count++;} while (target.count < 3);
// Abstract method call in object initializationOtherClass other = new OtherClass() {@Overridepublic synchronized int process(TargetClass t) {return t.count;}};int processed = other.process(target);
// Call target's private method in for loopfor (int i = 0; i < 2; i++) {target.increment();}
List<String> result = new ArrayList<>();result.add(local.getInfo(target));return result;}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3077 {@Testpublic void testOverridingMethod() {SourceClass source = new SourceClass() {};List<String> result = source.handle();assertEquals(1, result.size());assertEquals("test", result.get(0));}}