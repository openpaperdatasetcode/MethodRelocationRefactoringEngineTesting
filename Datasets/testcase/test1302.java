package test.refactor.movemethod;
import java.lang.reflect.Method;
// Source record class (private modifier, same package, with anonymous + local inner class)private record SourceRecord(String sourceField) {public void processTargetField(TargetRecord target) {// Per_condition: source uses target's fieldString targetFieldValue = target.targetField();
// Local inner class (source feature)class LocalInner {public void useTargetField() {System.out.println("LocalInner uses target field: " + targetFieldValue);}}new LocalInner().useTargetField();
// Anonymous inner class (source feature)Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous uses target field: " + targetFieldValue);}};anonymous.run();
// For statementfor (int i = 0; i < 2; i++) {// Variable callHelperClass helper = new HelperClass();String result = helper.callInInit(target); // Call others_class method in object initializationSystem.out.println("For loop result: " + result);}
// Used_by_reflectiontry {Method targetMethod = TargetRecord.class.getDeclaredMethod("targetMethod");targetMethod.invoke(target);} catch (ReflectiveOperationException e) {// No_new_exception: rethrow without wrappingthrow new RuntimeException(e);}}}
// Target record class (abstract modifier, with static nested class)abstract record TargetRecord(String targetField) {// Target_feature: static nested classpublic static class TargetStaticNested {public static String getNestedData() {return "StaticNestedData";}}
public abstract void targetMethod();}
// Others_class for call_method (default modifier, constructor + this.methodName)class HelperClass {// Constructor (call_method target_feature)public HelperClass() {}
// Call_method: default modifier, returns String, in object initializationString callInInit(TargetRecord target) {// This.methodName(arguments) (call_method target_feature)return this.processTargetData(target) + TargetRecord.TargetStaticNested.getNestedData();}
private String processTargetData(TargetRecord target) {return "Processed: " + target.targetField();}}
// Concrete implementation of abstract target recordrecord ConcreteTargetRecord(String targetField) extends TargetRecord {public ConcreteTargetRecord(String targetField) {super(targetField);}
@Overridepublic void targetMethod() {System.out.println("Concrete target method executed");}}
// Test classpublic class MoveMethodTest5234 {public static void main(String[] args) {SourceRecord source = new SourceRecord("sourceValue");TargetRecord target = new ConcreteTargetRecord("targetFieldValue");source.processTargetField(target);}}