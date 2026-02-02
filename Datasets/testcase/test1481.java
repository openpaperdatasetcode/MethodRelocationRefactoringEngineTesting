package test.refactoring;
import java.sql.SQLException;import java.io.IOException;
// Parent interface for overriding featureinterface TargetOverrideInterface {TargetRecord overridingMethod(TargetRecord target);}
// Source class: record, default modifier, same package, has two local inner classesrecord SourceRecord(String sourceField) {// Source contains target's field (per_condition)private static final TargetRecord targetField = new TargetRecord("target_value");private static final String sourceStaticField = "source_static"; // For depends_on_static_field
// First local inner class (source feature)public void firstLocalMethod() {class FirstLocalInner {public void invokeStaticMethod() {SourceRecord.moveTargetMethod(targetField);}}new FirstLocalInner().invokeStaticMethod();}
// Second local inner class (source feature - duplicate)public void secondLocalMethod() {class SecondLocalInner {public void invokeStaticMethod() {SourceRecord.moveTargetMethod(targetField);}}new SecondLocalInner().invokeStaticMethod();}
// Target method: static, TargetClass Type return, public, source position// method types parameter is:none, per_condition: contains target parameter (TargetRecord)public static TargetRecord moveTargetMethod(TargetRecord targetParam) throws IOException, SQLException {// Variable call + depends_on_static_fieldString var = sourceStaticField + sourceField;targetParam.targetStaticNested.staticField = var; // expression statement
// IOException featureif (var.isEmpty()) {throw new IOException("Static field is empty");}
// SQLException featureif (targetParam.value() == null) {throw new SQLException("Target value is null");}
// Overriding feature (synchronized, inner_class, overriding, super.methodName())class InnerOverrideClass implements TargetOverrideInterface {@Overridepublic synchronized TargetRecord overridingMethod(TargetRecord target) { // method_feature:1, overridingsuper.toString(); // super.methodName()return target;}}
// pos: do-whileint i = 0;do {InnerOverrideClass overrideObj = new InnerOverrideClass();targetParam = overrideObj.overridingMethod(targetParam);i++;} while (i < 2);
// this.methodName(arguments) (refers to static method via record's implicit this)TargetRecord recursiveResult = SourceRecord.this.moveTargetMethodHelper(targetParam);
// No new checked exception (only declares IOException/SQLException as required)return recursiveResult;}
// Helper method for this.methodName(arguments)private TargetRecord moveTargetMethodHelper(TargetRecord target) {return target;}
// Override violation: same-signature method with incompatible return typepublic static String moveTargetMethod(TargetRecord targetParam, String param) {return "";}}
// Target class: record, public, has static nested class (target_feature)public record TargetRecord(String value) {// Static nested class (target_feature)public static class TargetStaticNested {public String staticField = "target_static";}
// Target static nested class instancepublic static final TargetStaticNested targetStaticNested = new TargetStaticNested();
// Member inner class (target_inner_rec: method's target position)public class TargetInnerRecClass {public TargetRecord getTargetInstance() {return TargetRecord.this;}}
// Target inner rec class instancepublic final TargetInnerRecClass targetInnerRecClass = new TargetInnerRecClass();
// Override violation: same method signature with non-static accesspublic TargetRecord moveTargetMethod(TargetRecord targetParam) {return targetParam;}}