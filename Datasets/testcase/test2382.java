package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Function;
interface MyInterface {}
public record SourceClass<T>(T value) implements MyInterface {class SourceInner {}
void localInnerMethod() {class LocalInner {} // Local inner class}
strictfp List<String> varargsMethod(TargetClass<String>... targets) {// Generic method in array initializationTargetClass<String>[] genericArr = {OthersClass.<String>genericMethod(3, "a", "b", "c")};
// Expression statementList<String> result = new ArrayList<>();for (TargetClass<String> target : targets) {result.add(target.value());TargetClass<String>.TargetInner inner = target.new TargetInner();inner.process();}
// Variable callfor (TargetClass<String> t : targets) {String var = t.value();t.new TargetInner().setData(var);}
// Raw typeTargetClass rawTarget = new TargetClass("raw");
// Requires try-catchtry {int parsed = Integer.parseInt(targets[0].value());result.add(String.valueOf(parsed));} catch (NullPointerException | NumberFormatException e) {result.add("error");}
return result;}}
protected record TargetClass(U value) extends ParentRecord {
class TargetInner {
U data;
void process() {}void setData(U data) { this.data = data; }}}
class ParentRecord<V> {}
class OthersClass {static <T> TargetClass<T> genericMethod(int count, T... args) {return new TargetClass<>(args[0]);}
int callMethod(SourceClass<String> source) throws Exception {// Exception throwing statementsif (source == null) {throw new Exception("Source is null",source.new SourceInner().new TargetClass<String>.TargetInner().method());}return 0;}}
