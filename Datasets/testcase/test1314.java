package test.refactor.movemethod;
import diff.pkg.OtherPackageService;import java.io.IOException;
// Parent class for super constructor invocation and SuperFieldAccessclass ParentClass {public String parentSuperField = "ParentSuperField";
public ParentClass(String param) {}}
// Interface for source_class implements featureinterface Executable {void execute(TargetClass target) throws IOException;}
// Sealed source class (strictfp, same package, implements + permits + member inner + anonymous inner class)public sealed class SourceClass extends ParentClass implements Executable permits SourceSubClass {// Feature: member inner classpublic class MemberInnerClass {public void useTargetField(TargetClass target) {System.out.println("Inner uses target field: " + target.targetField);}}
// Super constructor invocationpublic SourceClass() {super("superParam");}
// Method to be refactored: instance, public, void return, requires_throws@Overridepublic void execute(TargetClass targetParam) throws IOException { // per_condition// Per_condition: source contains target's fieldString targetFieldValue = targetParam.targetField;
// Public SuperFieldAccess (numbers:1, modifier:public, exp:SuperFieldAccess)String superField = super.parentSuperField;
// Variable call: target's static nested classTargetClass.TargetStaticNested nested = new TargetClass.TargetStaticNested();nested.process(targetFieldValue);
// Variable call: member inner classMemberInnerClass inner = new MemberInnerClass();inner.useTargetField(targetParam);
// Feature: anonymous inner classRunnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous uses target field: " + targetFieldValue);}};anonymous.run();
// ThrowStatement (private, diff_package_others pos, this.field x3)OtherPackageService service = new OtherPackageService();try {service.validate(targetParam.thisField1); // this.field 1service.validate(targetParam.thisField2); // this.field 2service.validate(targetParam.thisField3); // this.field 3
// Call_method: sub_class, final, instanceReference::methodName in Lambda expressionsSourceSubClass subClass = new SourceSubClass();Runnable lambda = () -> subClass.calculate(targetParam::getFieldLength);lambda.run();} catch (IllegalArgumentException e) {// ThrowStatement implementationthrow new IOException("Validation failed", e);}}
// Private helper for ThrowStatement featureprivate void validateField(String field) throws IllegalArgumentException {if (field == null) {throw new IllegalArgumentException("Field cannot be null");}}}
// Permitted subclass (for call_method: sub_class type)public final class SourceSubClass extends SourceClass {// call_method: final modifier, normal type, returns intpublic final int calculate(java.util.function.Supplier<Integer> fieldLengthSupplier) {return fieldLengthSupplier.get() * 2;}}
// Target class (default modifier, target_feature: static nested class)class TargetClass {// Target field (per_condition)public String targetField = "TargetFieldValue";
// this.field for ThrowStatement target_feature (3 fields)public String thisField1 = "Field1";public String thisField2 = "Field2";public String thisField3 = "Field3";
// Target_feature: static nested classpublic static class TargetStaticNested {public void process(String data) {System.out.println("Static nested processed: " + data);}}
public int getFieldLength() {return targetField.length();}}
// diff.pkg for diff_package_others pospackage diff.pkg;
public class OtherPackageService {public void validate(String field) throws IllegalArgumentException {if (field.isBlank()) {throw new IllegalArgumentException("Invalid field: " + field);}}}
// Test classpackage test.refactor.movemethod;
import java.io.IOException;
public class MoveMethodTest5251 {public static void main(String[] args) {SourceClass source = new SourceSubClass();TargetClass target = new TargetClass();try {source.execute(target);} catch (IOException e) {e.printStackTrace();}}}