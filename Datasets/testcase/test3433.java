package test;
// Default source class with member inner and anonymous inner classesclass SourceClass {private String outerData = "source_outer";
// Member inner class (source_class feature)class MemberInner {class InnerRec {// Protected instance method (no type parameters, position: source_inner_rec)protected int process(TargetClass target) {// Type declaration statementclass LocalProcessor {}new LocalProcessor();
// Uses outer thisString combinedData = SourceClass.this.outerData + "_" + target.getData();
// Depends on static fieldif (TargetClass.staticField != 1) return -1;
// Same_package_target LabeledStatement positiontarget.validateStaticField();
variableCall(target);return combinedData.length();}
private void variableCall(TargetClass target) {target.doTask();}}}
// Anonymous inner class (source_class feature)private final Runnable anonymousTask = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous task: " + outerData);}};
public void triggerProcess(TargetClass target) {new MemberInner().new InnerRec().process(target);anonymousTask.run();}}
// Protected target class with local inner class (target_feature)protected class TargetClass {public static int staticField = 1; // ClassName.field=1private String data = "target_data";
public String getData() {return data;}
public void doTask() {// Local inner class (target_feature)class LocalHelper {void enhance() {data += "_enhanced";}}new LocalHelper().enhance();}
// LabeledStatement (protected modifier, target_feature)protected void validateStaticField() {labeled_check: { // LabeledStatementif (staticField == 1) break labeled_check;throw new IllegalArgumentException("Static field mismatch");}}}