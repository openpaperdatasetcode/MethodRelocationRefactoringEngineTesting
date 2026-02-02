package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Function;
private sealed class SourceClass implements Processable permits SourceSubClass {// Static nested classpublic static class SourceStaticNested {}
// Anonymous inner classprivate Runnable initializer = new Runnable() {@Overridepublic void run() {new SourceClass().instanceMethod(new TargetClass<>().new InnerRec(1, "init"));}};
List<String> instanceMethod(TargetClass<String>.InnerRec targetRec) {List<String> result = new ArrayList<>();
// Type declaration statementTargetClass<String> target = new TargetClass<>();OtherClass helper = new OtherClass();
// Constructor invocationTargetClass<String>.InnerRec newRec = target.new InnerRec(2, "new_data");
// Expression statementresult.add(targetRec.value());result.add(newRec.value());
// Variable callresult.add(target.getInfo(targetRec));
// Do statementint i = 0;do {result.add("do_" + i);i++;} while (i < 2);
// Switch statementswitch (targetRec.id()) {case 1:result.add("id_is_one");break;case 2:result.add("id_is_two");break;default:result.add("id_default");}
// Object initialization with instance others_class methodTargetClass<String> processedTarget = helper.createTarget(targetRec);result.add(processedTarget.getInfo(newRec));
// Ternary operator with parent_class generic method referenceFunction<TargetClass<String>.InnerRec, String> formatter =targetRec.id() > 1 ? TargetParent::formatLong : TargetParent::formatShort;result.add(formatter.apply(targetRec));
return result;}
@Overridepublic void process() {}}
final class SourceSubClass extends SourceClass {}
interface Processable {void process();}
class TargetClass<T> {private T field;
public TargetClass() {// Local inner class with type parameterclass LocalHandler {
U process(U data) {
return data;
}
}
field = (T) new LocalHandler<String>().process("default");
}
public String getInfo(InnerRec<T> rec) {return rec.id() + ":" + rec.value();}
public record InnerRec(int id, U value) {}
}
class TargetParent {public static <T> String formatLong(TargetClass<T>.InnerRec<T> rec) {return "LongFormat[" + rec.id() + ":" + rec.value() + "]";}
public static <T> String formatShort(TargetClass<T>.InnerRec<T> rec) {return rec.id() + ":" + rec.value();}}
class OtherClass {// Protected instance method returning TargetClass Typeprotected <T> TargetClass<T> createTarget(TargetClass<T>.InnerRec<T> rec) {TargetClass<T> target = new TargetClass<>();return target;}}