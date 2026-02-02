package test.refactor.movemethod;
// Parent interface for target_class implementsinterface TargetInterface {void interfaceMethod();}
// Parent record for target_class extendsrecord ParentRecord(String parentField) {}
// Source record: default modifier, same package with target, permits featurerecord SourceRecord(String sourceData, TargetRecord targetField) permits ExtendedSourceRecord { // per_condition: source contains target's field// Static nested class (source_class feature)public static class StaticNestedClass {public static String staticField = "staticData"; // For depends_on_static_field}
// Member inner class (source_class feature)public class MemberInnerClass {public void innerMethod() {}}
// Target method to move (normal, protected, return TargetClass Type)protected TargetRecord protectedNormalMethod() {// Empty statement;
// If statement + NullPointerException riskif (targetField == null) {throw new NullPointerException("Target field cannot be null");}
// Super constructor invocation (implicit in record, explicit via local class)class SuperInvoker extends ParentRecord {public SuperInvoker() {super("parentValue"); // Super constructor invocation}}new SuperInvoker();
// Synchronized statementsynchronized (this) {System.out.println("Synchronized block");}
// Type declaration statement with bounds (with_bounds)class BoundedType<T extends CharSequence & Comparable<T>> {T value;}BoundedType<String> boundedInstance = new BoundedType<>();
// Super keywordssuper.toString();
// Variable callMemberInnerClass memberInner = new MemberInnerClass();memberInner.innerMethod();TargetRecord.MemberInnerClass targetInner = targetField.new MemberInnerClass();targetInner.innerMethod();
// Depends on static fieldString staticData = StaticNestedClass.staticField;
// No new exceptionreturn targetField;}}
// Extended record for source_class permits featurerecord ExtendedSourceRecord(String sourceData, TargetRecord targetField) extends SourceRecord {public ExtendedSourceRecord {super(sourceData, targetField);}}
// Target record: sealed, extends ParentRecord, implements TargetInterface (target_feature)sealed record TargetRecord(String targetData) extends ParentRecord implements TargetInterface permits ExtendedTargetRecord {// Member inner class (target_feature)public class MemberInnerClass {public void innerMethod() {}}
@Overridepublic void interfaceMethod() {}}
// Permitted extended record for target_class sealed featurerecord ExtendedTargetRecord(String targetData) extends TargetRecord {public ExtendedTargetRecord {super(targetData);}}
// Test classimport org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodRefactoring5267Test {@Testpublic void testMethodBeforeRefactoring() {TargetRecord target = new TargetRecord("targetValue");SourceRecord source = new SourceRecord("sourceValue", target);
// Execute target methodTargetRecord result = source.protectedNormalMethod();assertSame(target, result);
// Verify NullPointerExceptionSourceRecord nullSource = new SourceRecord("nullSource", null);assertThrows(NullPointerException.class, nullSource::protectedNormalMethod);
// Verify static field dependencyassertEquals("staticData", SourceRecord.StaticNestedClass.staticField);
// Verify target_class features (extends, implements, member inner class)assertTrue(TargetRecord.class.isSealed());assertTrue(ParentRecord.class.isAssignableFrom(TargetRecord.class));assertTrue(TargetInterface.class.isAssignableFrom(TargetRecord.class));assertNotNull(target.new MemberInnerClass());}}