package test;
import java.util.List;import java.util.function.Supplier;
// Source record class (default modifier, static nested class + member inner class)record SourceRecord(String data) {protected String outerProtectedField = "protected_data"; // For access_outer_protectedprivate TargetRecord targetField = new TargetRecord(10); // Per condition: source contains target field
// Static nested class (source feature)public static class SourceStaticNested {}
// Member inner class (source feature)public class SourceMemberInner {// Protected instance method feature (1, source, instance, outerInstance.new InnerClass().methodName(), pos: Lambda expressions)protected Object featureMethod() {return "feature_result";}}
// Instance method (final access modifier, returns Object, parameter type: List)public final Object instanceMethod(List<String> params) {// Access outer protected fieldString protectedVal = this.outerProtectedField;
// Variable callTargetRecord.TargetInnerRec innerRec = targetField.new TargetInnerRec();innerRec.recursiveMethod();targetField.process(data);
// Instance method feature in Lambda expressionsSupplier<Object> supplier = () -> new SourceRecord(data).new SourceMemberInner().featureMethod();Object featureResult = supplier.get();
// Requires_try_catchtry {innerRec.riskyOperation();} catch (Exception e) {// Handle exception}
return featureResult;}}
// Target record class (default modifier, anonymous inner class)record TargetRecord(int value) {public int targetField = 20; // Field for per_condition
public void process(String data) {}
// Target inner recursive class (target_inner_rec)public class TargetInnerRec {public void recursiveMethod() {}
public void riskyOperation() throws Exception {}}
// Anonymous inner class (target_feature){Runnable anon = () -> System.out.println("Target anonymous inner class");}}