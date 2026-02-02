package other;
import java.util.function.Consumer;
public record TargetRecord(String name, int value) {public class TargetInner {private String details;
public TargetInner(String details) {this.details = details;}
public String getFullInfo() {return name + ":" + value + ":" + details;}}}
package test.refactoring.movemethod;
import other.TargetRecord;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.ArrayList;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface RecordMethodAnnot {}
public record SourceRecord<T extends Number>(String id) {public static class StaticNested {public void processInner(TargetRecord.TargetInner inner) {System.out.println(inner.getFullInfo());}}
@RecordMethodAnnotpublic final Object process() {// Anonymous inner classRunnable initializer = new Runnable() {@Overridepublic void run() {System.out.println("SourceRecord initialized");}};initializer.run();
TargetRecord target = new TargetRecord("test", 1);TargetRecord.TargetInner inner = target.new TargetInner("details");Object varCall = target.name();
// LabeledStatement featureprocessLoop: {if (target.value() != 3) {break processLoop;}varCall = target.value();}
// NumberLiteral (1)int threshold = 1;
// With boundsList<T> numbers = new ArrayList<>();numbers.add((T) Integer.valueOf(target.value()));
// For loop with others class instance methodOtherClass other = new OtherClass();for (int i = 0; i < 2; i++) {other.log(inner.getFullInfo());}
// Overload existsreturn process(inner);}
public final Object process(TargetRecord.TargetInner inner) {// Overloaded methodreturn inner.getFullInfo();}}
package test.refactoring.movemethod;
class OtherClass {public void log(String message) {System.out.println(message);}}
import org.junit.Test;import static org.junit.Assert.*;import other.TargetRecord;
public class MoveMethodTest3121 {@Testpublic void testOverloadingMethod() {SourceRecord<Integer> source = new SourceRecord<>("src1");Object result = source.process();assertEquals("test:1:details", result);}}
