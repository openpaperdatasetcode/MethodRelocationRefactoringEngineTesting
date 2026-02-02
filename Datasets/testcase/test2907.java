import java.io.IOException;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnot {}
public record SourceRecord(String sourceField) {class MemberInner {class InnerRec {@MethodAnnotprotected TargetRecord methodToMove(TargetRecord... targets) throws IOException {String var = "localVar";this.var = var;
TargetRecord[] initialized = {new TargetRecord("init1"),new TargetRecord("init2"),new TargetRecord("init3")};
for (TargetRecord target : targets) {target.new InnerRec().normalMethod(3);System.out.println(target.targetField());}
return targets.length > 0 ? targets[0] : new TargetRecord("default");}}}
Runnable anonymous = new Runnable() {@Overridepublic void run() {try {new MemberInner().new InnerRec().methodToMove(new TargetRecord("anon"));} catch (IOException e) {}}};}

private record TargetRecord (String targetField) extends ParentRecord {
class InnerRec {
void normalMethod (int num) {
System.out.println ("Target inner method:" + num);
}
}
}
abstract class ParentRecord {}