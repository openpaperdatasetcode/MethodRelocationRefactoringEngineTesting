package sourcepkg;
import java.util.List;import java.util.ArrayList;import targetpkg.TargetRecord;
sealed record SourceRecord(String content) permits SourceRecord.SubRecord {// Member inner class (source feature)public class SourceMemberInner {public void innerMethod() {}}
// Local inner class (source feature)public List<String> createLocalInner() {class SourceLocalInner {public List<String> process(TargetRecord target) {return target.getDataList();}}return new SourceLocalInner().process(new TargetRecord("init"));}
// Normal method (default access modifier, returns List<String>)List<String> normalMethod(TargetRecord targetParam) {// Access outer private (record component is implicitly private)String privateVal = this.content;
List<String> result = new ArrayList<>();// Type declaration statementSourceMemberInner memberInner = new SourceMemberInner();
// ExpressionStatement (static, target_feature: this.field x2, pos: inner class)class ExprInner {public void process() {static int sum = 0;sum += targetParam.field1 + targetParam.field2;result.add(String.valueOf(sum));}}new ExprInner().process();
// Super keywords (record implicitly extends Record)super.toString();
// Variable call + access_instance_methodtargetParam.variableCall(privateVal);memberInner.innerMethod();
// Override violation: target's anonymous inner class method without @OverrideTargetRecord.AnonymousInner overrideAnon = new TargetRecord.AnonymousInner() {public void method() {}};
// Return statementreturn result;}
// Permitted subclass for sealed recordpublic static non-sealed class SubRecord extends SourceRecord {public SubRecord(String content) {super(content);}}}
package targetpkg;
import java.util.List;import java.util.ArrayList;
public record TargetRecord(String value) {public int field1 = 10; // Field for per_conditionpublic int field2 = 20; // Field for per_condition
// Anonymous inner class (target_feature)public AnonymousInner createAnonymousInner() {return new AnonymousInner() {@Overridepublic void method() {}};}
public interface AnonymousInner {void method();}
public void variableCall(String data) {}
public List<String> getDataList() {return new ArrayList<>();}}