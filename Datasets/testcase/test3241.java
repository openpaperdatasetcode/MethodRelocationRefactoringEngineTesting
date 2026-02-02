package test;
import java.util.ArrayList;import java.util.List;
// Interface for overriding featureinterface Processable {TargetClass process(TargetClass target);}
/**
Public target class with local inner class (target_feature)*/public class TargetClass {public static String STATIC_FIELD = "target_static"; // For depends_on_static_fieldprivate String data;
public TargetClass(String data) {this.data = data;}
public void doTask() {}
/**
Protected static call_method (ClassName.methodName(arguments))
*/
protected static List<String> targetStaticCall(String... params) {
List<String> result = new ArrayList<>();
for (String param : params) {
result.add(STATIC_FIELD + "_" + param);
}
return result;
}
public String getData() {return data;}
public void setData(String data) {this.data = data;}
// Local inner class (target_feature)public List<String> targetLocalProcess() {class LocalCollector {public List<String> collect() {return new ArrayList<>(List.of(data, STATIC_FIELD));}}return new LocalCollector().collect();}}
/**
Default source class with two member inner classes*/class SourceClass implements Processable {private String outerPrivateField = "source_private"; // For access_outer_private
// First member inner class (source_class feature)class InnerClass1 {public String assist(TargetClass target) {return outerPrivateField + "_" + target.getData();}}
// Second member inner class (source_class feature)class InnerClass2 implements Processable {/**
Public overriding method_feature (1 parameter, source, overriding)
*/
@Override
public TargetClass process(TargetClass target) {
// this.methodName(arguments)
this.updateTargetData(target, "overridden");
return target;
}
private void updateTargetData(TargetClass target, String suffix) {target.setData(target.getData() + "_" + suffix);}}
/**
Protected normal method (position: source_inner)*/protected List<String> process(TargetClass target) {List<String> result = new ArrayList<>();
// Access outer private fieldString privateCombined = outerPrivateField + "_" + target.getData();result.add(privateCombined);
// Depends on static fieldresult.add(TargetClass.STATIC_FIELD);
// Raw typeList rawList = new ArrayList();rawList.add(privateCombined);
// Type declaration statementclass LocalProcessor {public void validate() {if (target.getData().isEmpty()) {throw new IllegalStateException("Data empty");}}}new LocalProcessor().validate();
// Variable callvariableCall(target);String assistResult = new InnerClass1().assist(target);result.add(assistResult);
// Object initialization (overriding method_feature position)TargetClass processedTarget = new InnerClass2().process(target);result.add(processedTarget.getData());
// Synchronized statementsynchronized (target) {target.setData(target.getData() + "_synced");}
// Empty statement;
// this.methodName(arguments)this.addStaticData(result, target);
// for (call_method position)List<String> staticCallResult = new ArrayList<>();for (int i = 0; i < 2; i++) {staticCallResult.addAll(TargetClass.targetStaticCall("param" + i));}result.addAll(staticCallResult);
result.addAll(target.targetLocalProcess());return result;}
private void addStaticData(List<String> result, TargetClass target) {result.add(TargetClass.STATIC_FIELD + "_" + outerPrivateField);}
private void variableCall(TargetClass target) {target.doTask();}
/**
Implement interface method
*/
@Override
public TargetClass process(TargetClass target) {
new InnerClass2().process(target);
return target;
}
}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) {
TargetClass target = new TargetClass("test_data");
SourceClass source = new SourceClass();
List<String> result = source.process(target);
assert result.size() == 10 : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}