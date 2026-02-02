package test.refactoring.movemethod;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
// Target record with anonymous inner classrecord TargetRecord(String id, int value) {public TargetRecord {// Anonymous inner class in targetSupplier<String> idSupplier = new Supplier<>() {@Overridepublic String get() {return id;}};if (idSupplier.get() == null) {throw new IllegalArgumentException("ID cannot be null");}}
public class TargetInner {public String suffix;
public TargetInner(String suffix) {this.suffix = suffix;}
public String getCombined() {return id() + "" + value() + "" + suffix;}
public void setSuffix(String suffix) {this.suffix = suffix;}}}
// Private source recordprivate record SourceRecord<T>(T data) {// Static nested classstatic class SourceStaticNested {static String format(String input) {return input.toLowerCase();}}
public List<String> process(TargetRecord.TargetInner inner) {List<String> results = new ArrayList<>();
// Local inner classclass ResultHandler {void add(String item) {results.add(SourceStaticNested.format(item));}}ResultHandler handler = new ResultHandler();
// Variable callObject varCall = inner.suffix;handler.add(varCall.toString());
// Access instance methodString combined = inner.getCombined();handler.add(combined);
// Constructor invocationTargetRecord newTarget = new TargetRecord("new_id", 99);TargetRecord.TargetInner newInner = newTarget.new TargetInner("new_suffix");handler.add(newInner.getCombined());
// Assert statementassert inner.suffix != null : "Inner suffix cannot be null";
// IfStatement with obj.field (1 occurrence) in inner classclass ConditionChecker {transient void checkLength() {if (inner.suffix.length() > 5) {results.add("long_suffix");} else {results.add("short_suffix");}}}new ConditionChecker().checkLength();
// CaseDefaultExpression (2 occurrences)String caseResult = switch (inner.suffix.length()) {case 1, 2 -> "short";case 3, 4 -> "medium";default -> "long";};handler.add(caseResult);
String defaultCase = switch (valueFromInner(inner)) {case 0 -> "zero";case 1 -> "one";default -> "other";};handler.add(defaultCase);
// Used by reflectiontry {Method method = TargetRecord.TargetInner.class.getMethod("getCombined");String reflected = (String) method.invoke(inner);handler.add("reflected:" + reflected);} catch (Exception e) {e.printStackTrace();}
return results;}
private int valueFromInner(TargetRecord.TargetInner inner) {return inner.suffix.length();}}
import org.junit.Test;import static org.junit.Assert.*;import java.util.List;import java.lang.reflect.Constructor;
public class MoveMethodTest3190 {@Test@SuppressWarnings("unchecked")public void testInstanceMethod() throws Exception {// Create source record via reflection (private)Constructor<SourceRecord<String>> sourceConstructor =(Constructor<SourceRecord<String>>) SourceRecord.class.getDeclaredConstructor(Object.class);sourceConstructor.setAccessible(true);SourceRecord<String> source = sourceConstructor.newInstance("source_data");
// Create target and inner instancesTargetRecord target = new TargetRecord("test_id", 5);TargetRecord.TargetInner inner = target.new TargetInner("suffix");
// Process and verifyList<String> result = source.process(inner);assertEquals(8, result.size());assertTrue(result.contains("suffix"));assertTrue(result.contains("test_id_5_suffix"));assertTrue(result.contains("new_id_99_new_suffix"));}}
