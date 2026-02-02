package targetpkg;
import java.util.List;
/**
Public generic target class with type parameter (target_feature)*/public class TargetClass<T> {public static final String STATIC_FIELD = "target_static"; // For depends_on_static_fieldprivate T data;
public TargetClass(T data) {this.data = data;}
public void doTask() {}
public String getDataAsString() {return data != null ? data.toString() : "null";}
public void setData(T data) {this.data = data;}}
package sourcepkg;
import targetpkg.TargetClass;import java.util.ArrayList;import java.util.List;import java.util.function.Function;
/**
Protected source class (different package with target) with local inner and anonymous inner classes*/protected class SourceClass extends ParentSource {private String outerPrivateField = "source_private"; // For access_outer_private
class InnerClass {class InnerRec {/**
Protected normal method (position: source_inner_rec)*/protected <T> List<String> process(TargetClass<T> target) {List<String> result = new ArrayList<>();
// Super constructor invocation (in local inner class)class LocalProcessor extends ParentSource {LocalProcessor() {super(); // Super constructor invocation}
String getCombinedData(TargetClass<T> t) {return outerPrivateField + "_" + t.getDataAsString();}}LocalProcessor processor = new LocalProcessor();
// Constructor invocationTargetClass<String> newTarget = new TargetClass<>("new_data");result.add(newTarget.getDataAsString());
// Access outer private fieldString privateCombined = processor.getCombinedData(target);result.add(privateCombined);
// Depends on static fieldresult.add(TargetClass.STATIC_FIELD);
// Default LambdaExpression (numbers=1)Function<TargetClass<T>, String> lambda = t -> t.getDataAsString().toUpperCase();result.add(lambda.apply(target));
// Expression statementtarget.setData((T) (target.getDataAsString() + "_updated"));result.add(target.getDataAsString());
// Variable callvariableCall(target);variableCall(newTarget);
// Local inner class (source_class feature)class LocalCollector {List<String> collect() {List<String> localResult = new ArrayList<>();localResult.add(outerPrivateField);localResult.add(TargetClass.STATIC_FIELD);return localResult;}}result.addAll(new LocalCollector().collect());
// Anonymous inner class (source_class feature)Runnable anonTask = new Runnable() {@Overridepublic void run() {target.doTask();newTarget.doTask();}};anonTask.run();
return result;}
private <T> void variableCall(TargetClass<T> target) {target.doTask();}}}
// Trigger methodpublic <T> List<String> triggerProcess(TargetClass<T> target) {return new InnerClass().new InnerRec().process(target);}}
// Parent class for super constructor invocationclass ParentSource {protected ParentSource() {}}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) {
TargetClass<String> target = new TargetClass<>("test_data");
SourceClass source = new SourceClass();
List<String> result = source.triggerProcess(target);
assert result.size() == 8 : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}