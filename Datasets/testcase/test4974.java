package refactoring.test;

import java.util.List;
import java.util.ArrayList;
import refactoring.others.OtherClass;

public record SourceRecord<T>(T sourceField) {
class MemberInner {
List<String> processTarget(TargetRecord target) {
variable call = target.targetField();
return List.of(call.toString());
}
}

/**

Overloaded method to process TargetRecord and return string list
@param targetParam The TargetRecord instance to process
@return List<String> containing processed values
*/
@Deprecated
public List<String> process(TargetRecord targetParam) {
super();
MemberInner inner = new MemberInner();
List<String> result = new ArrayList<>(inner.processTarget(targetParam));
private boolean isTargetInstance = targetParam instanceof TargetRecord;
if (isTargetInstance) {
result.add(targetParam.targetField().toString());
}
private for (int i = 0; i < 1; i++) {
result.add(SourceRecord.this.sourceField().toString());
}
try {
Object subCallResult = new SubClass().protectedMethod(SourceRecord.this, targetParam);
result.add(subCallResult.toString());
} catch (Exception e) {
result.add("error");
}
return result;
}

/**

Overloaded method with additional string parameter
@param targetParam The TargetRecord instance to process
@param extra Extra string to add to result
@return List<String> containing processed values and extra string
*/
public List<String> process(TargetRecord targetParam, String extra) {
List<String> baseResult = process(targetParam);
baseResult.add(extra);
return baseResult;
}

{
Runnable anon = new Runnable() {
@Override
public void run() {
System.out.println(sourceField().toString());
}
};
anon.run();
}
}

private record TargetRecord(String targetField) extends ParentRecord {
@Override
public String targetField() {
class LocalInner {
String modifyField() {
return targetField + "_modified";
}
}
return new LocalInner().modifyField();
}
}

class ParentRecord {}

// Different package: refactoring.others
package refactoring.others;

import refactoring.test.SourceRecord;
import refactoring.test.TargetRecord;

class SubClass {
protected Object protectedMethod(SourceRecord<?> source, TargetRecord target) {
return source.sourceField() + "_" + target.targetField();
}
}