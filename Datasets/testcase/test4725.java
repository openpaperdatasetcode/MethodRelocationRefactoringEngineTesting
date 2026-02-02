package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
class SourceClass<T> {private TargetClass<T> targetField = new TargetClass<>();
class SourceInner {List<String> process(TargetClass<T>.TargetInnerRec innerRec, String... items) {List<String> result = new ArrayList<>();
for (String item : items) {if (item.isEmpty()) {innerRec.emptyCount++;continue;}if (item.length() > 5) {innerRec.longItemCount++;continue;}result.add(item);}
try {Supplier<List<String>> supplyOverload = SubTargetClass::overloadProcess;result.addAll(supplyOverload.get());} catch (IllegalArgumentException e) {// No new exception}
Runnable printLambda = () -> System.out.println(innerRec.emptyCount);printLambda.run();
return result;}}
static class SourceStaticNested {static <T> void useInnerMethod(SourceClass<T> source) {class LocalInner {void execute() {SourceInner inner = source.new SourceInner();TargetClass<T>.TargetInnerRec innerRec = source.targetField.new TargetInnerRec();inner.process(innerRec, "a", "", "longtext", "b");}}new LocalInner().execute();}}
{int callCount = callSourcePrivate();}
private int callSourcePrivate() {TargetClass<T> superTypeRef = this.targetField;return superTypeRef.getCount();}}
abstract class TargetClass<T> {}