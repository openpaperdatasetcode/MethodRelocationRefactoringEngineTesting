package sourcepkg;
import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;import targetpkg.TargetClass;import targetpkg.TargetParentClass;
protected class SourceClass implements Runnable {// Two member inner classes (source feature)public class FirstMemberInner {}public class SecondMemberInner {}
// Static code blocks (pos for instance method feature)static {TargetParentClass parent1 = new TargetParentClass();TargetParentClass parent2 = new TargetParentClass();TargetParentClass parent3 = new TargetParentClass();
List<String> list1 = parent1.new ParentInner().parentInstanceMethod();List<String> list2 = parent2.new ParentInner().parentInstanceMethod();List<String> list3 = parent3.new ParentInner().parentInstanceMethod();}
@Overridepublic void run() {}
// Instance method (final access modifier, void return type)public final void instanceMethod(TargetClass targetParam) {// Requires try-catchtry {// Raw type usageTargetClass rawTarget = targetParam;
// Depends on inner class: target's local inner classTargetClass.TargetLocalInner localInner = rawTarget.createLocalInner();
// Enhanced for statementList<String> dataList = new ArrayList<>(List.of("a", "b", "c"));for (String data : dataList) {localInner.variableCall(data);}
// MethodReference (numbers=1, modifier=final)final Supplier<List<String>> methodRef = localInner::getData;List<String> refResult = methodRef.get();
// Variable call to target class methodrawTarget.targetMethod();} catch (Exception e) {// No new exception}}}
package targetpkg;
import java.util.List;import java.util.ArrayList;
// Target parent class (for method_feature: parent_class)class TargetParentClass {public class ParentInner {public List<String> parentInstanceMethod() {return new ArrayList<>();}}}
// Target class (abstract, implements interface)abstract class TargetClass implements TargetInterface {// Local inner class (target feature)public TargetLocalInner createLocalInner() {class TargetLocalInner {public void variableCall(String data) {}public List<String> getData() {return new ArrayList<>();}}return new TargetLocalInner();}
public abstract void targetMethod();}
// Target interface (for target's implements feature)interface TargetInterface {}