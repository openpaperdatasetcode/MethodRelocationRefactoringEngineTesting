package test;
import java.lang.reflect.Method;
public record SourceRecord(String sourceField) {public class MemberInner {public <T extends CharSequence> void normalMethod(ProtectedTargetRecord target) {class LocalInner {private abstract int abstractMethod();
private int implementAbstract() {return new AbstractMethodImpl().abstractMethod();}
private class AbstractMethodImpl extends MemberInner {@Overrideprivate int abstractMethod() {super.toString();return 1;}}}LocalInner local = new LocalInner();
try {Method reflectMethod = ProtectedTargetRecord.class.getDeclaredMethod("targetMethod");reflectMethod.invoke(target);} catch (Exception e) {}
for (int i = 0; i < 1; i++) {variableCall(target);int result = local.implementAbstract();}}
private void variableCall(ProtectedTargetRecord target) {target.targetMethod();}}}
protected record ProtectedTargetRecord(String targetField) {public void targetMethod() {class TargetLocalInner {void print() {System.out.println(targetField);}}new TargetLocalInner().print();}}