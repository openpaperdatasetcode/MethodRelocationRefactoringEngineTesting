package test;
import java.util.function.Supplier;
public record SourceRecord(String sourceField) {public static class SourceStaticNested {public void useSource(SourceRecord source) {System.out.println(source.sourceField());}}
{new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class: " + SourceRecord.this.sourceField);}}.run();}
private void recursiveProcess(TargetRecord target, int depth) {if (depth <= 0) {return;}
String combinedField = target.targetField() + SourceRecord.this.sourceField;System.out.println(combinedField);
Supplier<Object> targetMethodSupplier = () -> new TargetRecord.TargetStaticNested().callFinalMethod(target);Object targetResult = targetMethodSupplier.get();System.out.println("Target method result: " + targetResult);
recursiveProcess(target, depth - 1);}}
sealed record TargetRecord(String targetField) permits TargetRecord.SubTarget {}
record TargetRecord.SubTarget(String targetField) extends TargetRecord(targetField) {public static class TargetStaticNested {public final Object callFinalMethod(TargetRecord target) {return "Processed: " + target.targetField();}}}
