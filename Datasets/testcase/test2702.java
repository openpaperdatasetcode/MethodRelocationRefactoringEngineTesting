package test;
import java.util.List;import java.io.IOException;
final class SourceClass {// Member inner class (source_feature)private class SourceMemberInner {public String getPrivateField() {return "outerPrivateValue";}}
public Object methodToMove(List<TargetClass> targetList) throws IOException {// Local inner class (source_feature)class SourceLocalInner {public void process(TargetClass target) {target.toString();}}
Object result = new Object();// Do statementdo {// Synchronized statementsynchronized (this) {for (TargetClass target : targetList) {// Variable call + contains target parameter (per_condition)target.toString();
// Raw type usageTargetClass rawTarget = target;
// Access outer private (via member inner class)SourceMemberInner inner = new SourceMemberInner();String privateVal = inner.getPrivateField();
// Requires_throws: check condition to throwif (target.targetField == null) {throw new IOException("Target field is null");}
new SourceLocalInner().process(rawTarget);result = privateVal + target.targetField;}}} while (targetList.isEmpty());
return result;}}
protected class TargetClass {public String targetField = "targetData"; // Source contains target's field (per_condition)
{// Anonymous inner class (source_feature)new Runnable() {@Overridepublic void run() {}};}}