package test;
public record SourceRecord(String data) {// Static nested class (source_feature)protected static class SourceStaticNested {protected String protectedField = "protectedValue";}
public final TargetRecord.TargetInner.TargetRec[] methodToMove(TargetRecord... targets) {super(); // Super constructor invocation
// Local inner class (source_feature)class SourceLocalInner {public TargetRecord.TargetInner.TargetRec process(TargetRecord target) {return target.new TargetInner().new TargetRec();}}
TargetRecord.TargetInner.TargetRec[] results = new TargetRecord.TargetInner.TargetRec[targets.length];SourceStaticNested staticNested = new SourceStaticNested();
for (int i = 0; i < targets.length; i++) {TargetRecord target = targets[i];// Variable call + contains target parameter (per_condition)target.toString();TargetRecord.TargetInner.TargetRec rec = new SourceLocalInner().process(target);
// Access outer protectedrec.field = staticNested.protectedField + target.data();
// Empty statement;
results[i] = rec;}
return results;}}
final record TargetRecord(String data) {public class TargetInner {public class TargetRec {public String field;}
// Local inner class (target_feature)public void createLocalInner() {class TargetLocalInner {}new TargetLocalInner();}}}