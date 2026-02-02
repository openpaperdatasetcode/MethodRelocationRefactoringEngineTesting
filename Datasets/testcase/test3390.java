package test;
import otherpackage.DiffPackageHelper;import java.lang.reflect.Field;import java.util.ArrayList;import java.util.List;
protected class SourceClass {protected String outerProtectedField = "protected_data";
class MemberInner {/**
Method javadoc: Processes target and returns it
@param target TargetClass instance
@return Processed TargetClass*/public TargetClass process(TargetClass target) {super.toString(); // Super keywords
// FieldAccess (numbers=1, public modifier)public String targetField = target.publicField;
// Access outer protected fieldtargetField += SourceClass.this.outerProtectedField;
// Local inner classclass LocalProcessor {void validate() {DiffPackageHelper.validateTarget(target); // ThrowStatement position}}new LocalProcessor().validate();
// Used by reflectiontry {Field field = TargetClass.class.getDeclaredField("privateField");field.setAccessible(true);field.set(target, "reflected_value");} catch (Exception e) {}
variableCall(target);callMethod(target);return target;}
private void variableCall(TargetClass target) {target.staticNested.doTask();}
// Call_method: parent_class, strictfp modifierstrictfp List<String> callMethod(TargetClass target) {List<String> result = new ArrayList<>();// Collection operations positionresult.forEach(ParentClass::staticProcess); // ClassName::methodNamereturn result;}}}
class TargetClass {public static int staticField = 1; // ClassName.field=1public String publicField = "target_data";private String privateField;
public static class StaticNested {public void doTask() {}}
public StaticNested staticNested = new StaticNested();}
class ParentClass {// Static method for call_method target_featurepublic static void staticProcess(String s) {}}
package otherpackage;
import test.TargetClass;
public class DiffPackageHelper {public static void validateTarget(TargetClass target) {// Private ThrowStatement (target_feature: ClassName.field=1)private int field = TargetClass.staticField;if (field != 1) throw new IllegalArgumentException("Invalid static field value");}}