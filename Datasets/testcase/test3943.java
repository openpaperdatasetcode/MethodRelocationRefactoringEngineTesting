import java.util.ArrayList;import java.util.List;
sealed abstract class SourceClass permits SourceImpl {private TargetClass targetField = new TargetClass();
private Object instanceMethod(TargetClass.MemberInner targetInner) {// SuperConstructorInvocation (modifier:static, target_feature:obj.field[3])new StaticHelper(targetInner.objField1, targetInner.objField2, targetInner.objField3);
// Call generic method from static code blockList<String> genericResult = StaticGenericMethod.process(targetField, 3);
// Variable call to target inner classvariableCall(targetInner);
return genericResult;}
private void variableCall(TargetClass.MemberInner inner) {inner.execute();}
// Static code block hosting generic method callstatic {TargetClass staticTarget = new TargetClass();StaticGenericMethod.process(staticTarget, 3);}
// Static helper class for SuperConstructorInvocationprivate static class StaticHelper {public StaticHelper(String field1, int field2, boolean field3) {// Constructor logic using target inner class fields}}
// Generic method (modifier:public, method_feature:3/source/generic/new ClassName().method())public static class StaticGenericMethod {public static <T extends TargetClass> List<String> process(T target, int count) {List<String> result = new ArrayList<>();if (count <= 0) return result;
result.add(target.new MemberInner().getData());result.addAll(process(new TargetClass(), count - 1)); // Recursive "new ClassName().method()" callreturn result;}}}
// Permitted non-sealed implementation of abstract source classnon-sealed class SourceImpl extends SourceClass {}
// Non-sealed target class with member inner classnon-sealed class TargetClass {public class MemberInner {// 3 fields for SuperConstructorInvocation target_featurepublic String objField1 = "field1";public int objField2 = 2;public boolean objField3 = true;
public String getData() {return objField1 + objField2;}
public void execute() {}}}