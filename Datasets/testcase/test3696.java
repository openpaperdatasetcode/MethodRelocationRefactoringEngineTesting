package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Function;
interface RecordVarargsProcessor {List<String> process(TargetRecord... targets);}
record SourceRecord(String data) implements RecordVarargsProcessor {public class MemberInner {public class DeepInner {List<String> varargsMethod(TargetRecord... targets) {class TypeDecl {String extractField(TargetRecord t) {return t.field();}}
TypeDecl typeDecl = new TypeDecl();List<String> results = new ArrayList<>();TargetRecord[] targetArray = new TargetRecord[2];
Function<TargetRecord[], List<String>> func = arr -> {List<String> list = new ArrayList<>();for (TargetRecord t : arr) {list.add(typeDecl.extractField(t));}return list;};
results.addAll(func.apply(targets));
new Runnable() {@Overridepublic void run() {for (TargetRecord t : targets) {t.processWithAnonymous();}}}.run();
for (TargetRecord target : targets) {TargetRecord processed = new InnerHelper().processTarget(target);results.add(processed.field());}
return results;}}}
protected class InnerHelper {public TargetRecord processTarget(TargetRecord target) {return processTarget(target, "default");}
public TargetRecord processTarget(TargetRecord target, String suffix) {return new TargetRecord(target.field() + "_" + suffix);}}
@Overridepublic List<String> process(TargetRecord... targets) {return new MemberInner().new DeepInner().varargsMethod(targets);}}
private record TargetRecord(String field) {public void processWithAnonymous() {Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println(field + "_anonymous");}};runnable.run();}}