package test.refactoring.movemethod;
import otherpackage.DiffPackageProcessor;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.reflect.Method;import java.util.List;import java.util.ArrayList;
@Retention(RetentionPolicy.RUNTIME)@interface ProcessAnnotation {}
// Non-sealed target record with static nested classnon-sealed record TargetRecord<T>(T id, int value) {public static class TargetStaticNested {public Object getProcessedValue(TargetRecord<?> record) {return record.id() + "_" + record.value();}}}
// Subclass of TargetRecordrecord SubTargetRecord(String id, int value, String extra) extends TargetRecord<String> {public SubTargetRecord {super(id, value);}}
// Public source record with type parameterpublic record SourceRecord<T extends CharSequence>(T data) {static class SourceStaticNested {}
public class SourceInner {@ProcessAnnotationpublic final void process(TargetRecord target, List<String> items) {
// Variable call
Object varCall = target.id();
// With bounds (type parameter U extends Number)U numericId = target.id();double idValue = numericId.doubleValue();
// Requires try-catch (reflection)try {// Used by reflectionMethod method = TargetRecord.TargetStaticNested.class.getMethod("getProcessedValue", TargetRecord.class);Object reflectedResult = method.invoke(new TargetRecord.TargetStaticNested(), target);System.out.println("Reflection result: " + reflectedResult);} catch (Exception e) {e.printStackTrace();}
// Switch statement with sub class method callswitch (items.size()) {case 0:Object result0 = SubTargetRecord.TargetStaticNested.getProcessedValue(new SubTargetRecord("sub", 0, "extra0"));break;case 1:Object result1 = SubTargetRecord.TargetStaticNested.getProcessedValue(new SubTargetRecord("sub", 1, "extra1"));break;default:Object resultDefault = SubTargetRecord.TargetStaticNested.getProcessedValue(new SubTargetRecord("sub", items.size(), "extraDefault"));}
// Call different package class for synchronized statementDiffPackageProcessor.process(target);}}}
package otherpackage;
import test.refactoring.movemethod.TargetRecord;
public class DiffPackageProcessor {public static <T> void process(TargetRecord<T> target) {// SynchronizedStatement with this.field (2 fields: id and value)private synchronized (target) {Object field1 = target.id(); // First field accessObject field2 = target.value(); // Second field accessSystem.out.println("Processed fields: " + field1 + ", " + field2);}}}
import org.junit.Test;import static org.junit.Assert.*;import java.util.Arrays;import java.util.List;
public class MoveMethodTest3177 {@Testpublic void testInstanceMethod() {SourceRecord<String> source = new SourceRecord<>("source_data");SourceRecord<String>.SourceInner inner = source.new SourceInner();TargetRecord<Integer> target = new TargetRecord<>(100, 200);List<String> items = Arrays.asList("item1", "item2");
inner.process(target, items);// Verify through side effects and reflection checks}}