package samepkg;
public record SourceRecord(int sourceField) {private int sourcePrivateField = 5;
@Deprecatedprivate int instanceMethod(TargetRecord targetParam) {for (int i = 0; i < 1; i++) {InnerClass inner = new InnerClass();TargetRecord result = inner.innerInstanceMethod();}
int count = 0;while (count < 1) {TargetRecord varCall = targetParam;int outerPrivate = SourceRecord.this.sourcePrivateField;count++;}
return targetParam.data();}
class InnerClass {public TargetRecord innerInstanceMethod() {return new TargetRecord("data");}}
{Runnable r = new Runnable() {@Overridepublic void run() {new SourceRecord(10).instanceMethod(new TargetRecord("anon"));}};}}
private record TargetRecord(int data) {}