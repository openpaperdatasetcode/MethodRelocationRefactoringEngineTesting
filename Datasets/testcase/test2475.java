package test.refactoring.movemethod;
import java.io.IOException;import java.util.function.Supplier;
// Private target record with local inner classprivate record TargetRecord(String id, int value) {public static final String STATIC_FIELD1 = "static1";public static final int STATIC_FIELD2 = 100;
public TargetRecord {// Local inner class in target recordclass Validator {boolean isValid() {return id != null && value > 0;}}if (!new Validator().isValid()) {throw new IllegalArgumentException("Invalid TargetRecord");}}
public class TargetInner {public String processId() {return id.toUpperCase();}
private String getCombined() {return id + "_" + value;}}}
// Same package other class for VariableDeclarationStatementclass SamePackageHelper {public static void processStaticFields() {// VariableDeclarationStatement with ClassName.field (2 static fields)static String field1 = TargetRecord.STATIC_FIELD1;static int field2 = TargetRecord.STATIC_FIELD2;System.out.println("Processed static fields: " + field1 + ", " + field2);}}
// Non-sealed source recordnon-sealed record SourceRecord(String sourceData) {/**
Varargs method to process TargetRecord and return base type
@param targets array of TargetRecord instances
@return total value sum
@throws IOException if target is invalid*/int process(TargetRecord... targets) throws IOException {// Super constructor invocation (implicit in record, explicit via derived inner)class DerivedRecord extends TargetRecord {DerivedRecord() {super("derived", 1);}}new DerivedRecord();
// Uses outer thisString outerData = this.sourceData();
// Variable callObject varCall = outerData;
// Depends on static fieldint staticBase = TargetRecord.STATIC_FIELD2;int total = 0;
// For statementfor (TargetRecord target : targets) {// Synchronized statementsynchronized (target) {total += target.value() + staticBase;}
// Expression statementTargetRecord.TargetInner inner = target.new TargetInner();varCall = inner.processId();}
// Lambda expressions with inner class method referenceSupplier<String> innerSupplier = TargetRecord.TargetInner::getCombined;System.out.println("Lambda result: " + innerSupplier.get());
// IOExceptionif (targets.length == 0) {throw new IOException("No targets provided");}
// Call same package helper for static field processingSamePackageHelper.processStaticFields();
return total;}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3172 {@Testpublic void testVarargsMethod() throws IOException {SourceRecord source = new SourceRecord("source_context");TargetRecord target1 = new TargetRecord("t1", 5);TargetRecord target2 = new TargetRecord("t2", 8);
int result = source.process(target1, target2);// Total = (5+100) + (8+100) = 213assertEquals(213, result);}}