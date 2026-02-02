package test.refactoring;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

abstract class ParentRecord {
private static Object parentStaticMethod(Collection<?> coll) {
List<Object> result = new ArrayList<>();
coll.forEach(item -> result.add(item.toString()));
return result;
}
}

strictfp record SourceClass(String sourceField) extends ParentRecord {
class SourceMemberInner {
private String innerData;

public SourceMemberInner(String innerData) {
this.innerData = innerData;
}

public String getInnerData() {
return innerData;
}

public void updateInnerData(String newData) {
this.innerData = newData;
}
}

private SourceClass(TargetClass target) {
this(target.targetField());

class SourceLocalInner {
Object processTargetInner(TargetClass.TargetInner inner) {
return inner.innerMethod(sourceField);
}
}

SourceLocalInner localInner = new SourceLocalInner();
SourceMemberInner memberInner = new SourceMemberInner("init_inner_data");

TargetClass.TargetInner targetInner = new TargetClass.TargetInner("target_inner_data");
Object varDeclExpr = localInner.processTargetInner(targetInner);

memberInner.updateInnerData(target.targetField() + "_updated");
String exprStmtResult = memberInner.getInnerData() + "|" + sourceField;
System.out.println(exprStmtResult);

variableCall(targetInner, "Processed target inner instance");

Collection<Object> coll = List.of(target, targetInner, memberInner);
Object callResult = ParentRecord.parentStaticMethod(coll);
System.out.println("Parent static method result: " + callResult);
}

public static SourceClass createInstance(TargetClass target) {
return new SourceClass(target);
}

private void variableCall(TargetClass.TargetInner targetInner, String message) {
System.out.printf("%s | Target inner data: %s%n",
message, targetInner.getInnerField());
}
}

record TargetClass(String targetField) {
static class TargetInner {
private String innerField;

public TargetInner(String innerField) {
this.innerField = innerField;
}

public String innerMethod(String param) {
return innerField + "_" + param;
}

public String getInnerField() {
return innerField;
}
}
}