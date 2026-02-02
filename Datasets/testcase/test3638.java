package test;
sealed public record SourceRecord(String sourceField) permits ExtendedSourceRecord {public class MemberInner {protected ProtectedTargetRecord instanceMethod(ProtectedTargetRecord target) {new ConstructorInvocation(target);; // Empty statement
new Runnable() {public void run() {SourceRecord.this.sourceField();}}.run();
Object methodRef = ProtectedTargetRecord::new;variableCall(target);
return target;}
private void variableCall(ProtectedTargetRecord target) {target.targetMethod();}}
private static class ConstructorInvocation extends ParentClass {public ConstructorInvocation(ProtectedTargetRecord target) {super();this.superField = target.superField;}}}
non-sealed record ExtendedSourceRecord(String sourceField) extends SourceRecord(sourceField) {}
class ParentClass {protected String superField = "parentData";}
protected record ProtectedTargetRecord(String targetField) extends ParentClass {public void targetMethod() {new Runnable() {public void run() {superField = "updatedSuper";}}.run();}}