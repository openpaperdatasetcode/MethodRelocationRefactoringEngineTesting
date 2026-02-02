package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;
interface Processable {List<String> process();}
public sealed enum SourceClass implements Processable permits SubSourceEnum {INSTANCE;
protected TargetEnum targetField = TargetEnum.TARGET;
class SourceInner {@MyAnnotationprotected List<String> instanceMethod(TargetEnum.TargetInnerRec rec) {if (rec == null) throw new NullPointerException("rec is null");
type declaration statement:class LocalType {String value;LocalType(String v) { value = v; }}
List<String> result = new ArrayList<>();TargetEnum.TargetInnerRec varCall = rec;
do {expression statement:result.add(varCall.getData());} while (result.size() < 1);
try {Supplier<TargetEnum> recursiveSupplier = () ->SourceClass.this.targetField.getNested().getInner().getTarget();TargetEnum recursiveResult = recursiveSupplier.get();result.add(recursiveResult.name());} catch (Exception e) {result.add("error");}
result.add(SourceClass.this.targetField.protectedField);return result;}}
@Overridepublic List<String> process() {return new SourceInner().instanceMethod(new TargetEnum.TargetInnerRec("data"));}}
final class SubSourceEnum extends SourceEnum {@Overridepublic List<String> process() {return new SourceInner().instanceMethod(new TargetEnum.TargetInnerRec("subData"));}}
enum TargetEnum {TARGET;
protected String protectedField = "targetProtected";
TargetNested getNested() {return new TargetNested();}
class TargetNested {TargetInner getInner() {return new TargetInner();}}
class TargetInner {TargetEnum getTarget() {return TargetEnum.this;}}
record TargetInnerRec(String data) {String getData() {Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println(data);}};anon.run();return data;}}}
@interface MyAnnotation {}