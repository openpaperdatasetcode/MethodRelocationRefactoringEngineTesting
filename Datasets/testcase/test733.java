import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
strictfp class TargetClass {String targetField = "targetValue";
// Anonymous inner class in target classRunnable targetRunnable = new Runnable() {@Overridepublic void run() {System.out.println(targetField);}};
public TargetClass() {}}
// Same package helper class for AssertStatement positionclass SamePackageHelper {private void assertStatement(TargetClass obj) {// obj.field featureassert obj.targetField != null : "Target field is null";// Numeric literal "2" featureassert obj.targetField.length() > 2 : "Field length less than 2";}}
public class SuperClass {protected List<String> method(TargetClass targetParam) {return new ArrayList<>();}
// Overload method for overload_exist featureprotected List<String> method(TargetClass targetParam, String extra) {return new ArrayList<>();}}
public class SourceClass extends SuperClass {private String outerField = "outerValue";
@Overrideprotected List<String> method(TargetClass targetParam) {// Super constructor invocationsuper();
// Expression statementtargetParam.targetRunnable.run();
// Variable callString var = outerField;variableCall(var);
// Uses outer thisSourceClass outerThis = SourceClass.this;String outerThisField = outerThis.outerField;
// Raw type featureList rawList = new ArrayList();rawList.add(targetParam.targetField);
// Used by reflectiontry {Method reflectMethod = SourceClass.class.getDeclaredMethod("method", TargetClass.class);reflectMethod.invoke(this, targetParam);} catch (Exception e) {e.printStackTrace();}
// Override violation check (implicit via @Override + correct signature)// AssertStatement in same_package_othersnew SamePackageHelper().assertStatement(targetParam);
// Return listList<String> result = new ArrayList<>();result.add(targetParam.targetField);return result;}
// Variable call helper methodprivate void variableCall(String var) {System.out.println(var);}
// Overload exist (additional overload)protected List<String> method(TargetClass targetParam, Integer num) {return new ArrayList<>();}}