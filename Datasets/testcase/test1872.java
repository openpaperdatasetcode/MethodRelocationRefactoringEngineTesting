package test;
import java.util.ArrayList;import java.util.List;
final class SourceClass<T> {// Static nested classpublic static class SourceStaticNested {}
// Static code block with 2 static sub_class methods (method chaining)static {TargetSubclass<String> sub = new TargetSubclass<>("static");Object result1 = sub.createInnerRec(1).setValue("one").get();Object result2 = sub.createInnerRec(2).setValue("two").get();System.out.println(result1 + ", " + result2);}
public final void varargsMethod(int... counts) {// Raw typeList rawList = new ArrayList();
// Array initialization with call method (constructor, method reference)TargetClass.InnerRec[] recs = counts.length > 0? new TargetClass.InnerRec[counts.length]: new TargetClass.InnerRec[1];for (int i = 0; i < recs.length; i++) {recs[i] = OtherClass::new TargetClass.InnerRec(i, "data" + i);}
// Do statementint i = 0;do {if (i >= counts.length) break;// Variable callrawList.add(recs[i].value());i++;} while (true);
// Empty statement;
// Requires try-catchtry {int val = Integer.parseInt(recs[0].value());rawList.add(val);} catch (NumberFormatException e) {// Handle exception}
// Return statementif (rawList.isEmpty()) return;}
// Local inner classpublic void useLocalInner() {class LocalHandler {void process(TargetClass<String> target) {varargsMethod(3, 5, 7);}}new LocalHandler().process(new TargetClass<>());}}
class TargetClass<T> {// Static nested classpublic static class TargetStaticNested {}
public record InnerRec(int id, T value) {public InnerRec setValue(T val) {return new InnerRec(id, val);}
public T get() {return value;}}}
class TargetSubclass<T> extends TargetClass<T> {public TargetSubclass(T value) {super();}
public static InnerRec createInnerRec(int id) {
return new InnerRec(id, null);
}
}
class OtherClass {public static <T> TargetClass.InnerRec createRec(int id, T value) {return new TargetClass.InnerRec(id, value);}}