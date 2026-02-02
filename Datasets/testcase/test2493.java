package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;
abstract class RecordParent {protected String parentData;
public RecordParent(String parentData) {this.parentData = parentData;}}
// Public target record with extends and static nested classpublic record TargetRecord(String id, int value) extends RecordParent {public TargetRecord {super(id);}
public static class TargetStaticNested {private String nestedInfo;
public TargetStaticNested(String nestedInfo) {this.nestedInfo = nestedInfo;}
public String getCombined(TargetRecord record) {return record.id() + "_" + nestedInfo;}}}
// Same package helper classclass SamePackageHelper {public static TargetRecord createTarget(String id, int value) {// ConstructorInvocation with this.field (1 occurrence)private String field = id;return new TargetRecord(field, value);}}
// Private source recordprivate record SourceRecord<T>(T data) {// Static nested classstatic class SourceStaticNested {static U convert(Object input) {
return (U) input;
}
}
// Abstract methodpublic abstract Object process(TargetRecord target);
// Concrete implementation example (for testing)public static SourceRecord<String> create(String data) {return new SourceRecord<>(data) {@Overridepublic Object process(TargetRecord target) {// Local inner classclass Processor {Object handle() {return target.id() + ":" + data;}}Processor processor = new Processor();
// Variable callObject varCall = target.value();
// Access outer private (record component is implicitly private)String outerData = this.data();
// Raw typeList rawList = new ArrayList();rawList.add(target.id());
// Labeled statementprocessing: {if (target.value() < 0) {break processing;}rawList.add(target.value());}
// Do statementint count = 0;do {rawList.add("item_" + count);count++;} while (count < target.value());
// Synchronized statementsynchronized (target) {rawList.add("synchronized_" + target.id());}
// Use same package helper for constructor invocationTargetRecord newTarget = SamePackageHelper.createTarget(outerData, target.value() + 1);rawList.add(newTarget.id());
return rawList;}};}}
import org.junit.Test;import static org.junit.Assert.*;import java.util.List;import java.lang.reflect.Constructor;
public class MoveMethodTest3197 {@Test@SuppressWarnings("unchecked")public void testAbstractMethod() throws Exception {// Create private SourceRecord via reflectionConstructor<SourceRecord<String>> sourceConstructor =(Constructor<SourceRecord<String>>) SourceRecord.class.getDeclaredConstructor(Object.class);sourceConstructor.setAccessible(true);SourceRecord<String> source = SourceRecord.create("source_data");
TargetRecord target = new TargetRecord("test_id", 2);
Object result = source.process(target);assertTrue(result instanceof List);List resultList = (List) result;assertEquals(7, resultList.size()); // id + value + 2 items + synchronized + newTarget.id}}