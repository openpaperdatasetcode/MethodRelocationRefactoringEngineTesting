package test;
import java.sql.SQLException;import java.util.ArrayList;import java.util.List;import java.util.function.Function;
// Interface for overriding featureinterface Processable {List<String> process(TargetRecord target);}
/**
Public target record with anonymous inner class (target_feature)*/public record TargetRecord(String data) {public void doTask() {}
public String getInstanceField() {return data + "_instance"; // Simulate instance field access}
// Anonymous inner class (target_feature)public Runnable getTargetTask() {return new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous task: " + data);}};}}
/**
Public source record with static nested and local inner classes*/public record SourceRecord(String sourceData) implements Processable {// Static nested class (source_class feature)public static class SourceStaticNested {public static Object staticProcess(SourceRecord source) {return source.sourceData + "_static_processed";}}
// Other class for "otherObject.process(this)"public static class OtherProcessor {public void process(SourceRecord source) {source.sourceData = source.sourceData + "_other_processed";}}
class InnerClass {class InnerRec {/**
Final instance method_feature (1 parameter, source, instance)
*/
public final Object instanceHelper(SourceRecord source) {
// ClassName::methodName
Function<SourceRecord, Object> staticMapper = SourceStaticNested::staticProcess;
return staticMapper.apply(source);
}
/**
Private overriding method (position: source_inner_rec)*/private List<String> process(TargetRecord target) {List<String> result = new ArrayList<>();// Uses outer thisSourceRecord outerThis = SourceRecord.this;
try {// Access instance fieldString instanceData = target.getInstanceField();result.add(instanceData);
// OtherObject.process(this)OtherProcessor otherObject = new OtherProcessor();otherObject.process(outerThis);result.add(outerThis.sourceData);
// For statementfor (int i = 0; i < 2; i++) {result.add(target.data() + "loop" + i);}
// do-while (method_feature position)int count = 0;do {Object helperResult = instanceHelper(outerThis);result.add(helperResult.toString());count++;} while (count < 1);
// Variable callvariableCall(target);target.getTargetTask().run();
// SQLException handlingif (instanceData.isEmpty()) {throw new SQLException("Instance field cannot be empty");}
// Local inner class (source_class feature)class LocalCollector {List<String> collect() {List<String> localResult = new ArrayList<>();localResult.add(outerThis.sourceData);localResult.add(target.data());return localResult;}}result.addAll(new LocalCollector().collect());
} catch (SQLException e) {result.add("SQLException caught");}
return result;}
private void variableCall(TargetRecord target) {target.doTask();}}}
/**
Implement interface method
*/
@Override
public List<String> process(TargetRecord target) {
return new InnerClass().new InnerRec().process(target);
}
}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) {
TargetRecord target = new TargetRecord("test_target");
SourceRecord source = new SourceRecord("test_source");
List<String> result = source.process(target);
assert result.size() == 7 : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}