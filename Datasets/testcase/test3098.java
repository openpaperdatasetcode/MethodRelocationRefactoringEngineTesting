import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
sealed class ParentGeneric<T> permits SourceClass {}
non-sealed class SourceClass<T> extends ParentGeneric<T> {static class StaticNested<T> {}
{Runnable r = new Runnable() {public void run() {new SourceClass<String>().process(new TargetClass<>());}};}
public final Object process(TargetClass<T> target) {super();TypeDeclaration<T> typeDecl = new TypeDeclaration<>();List<String> result = new ArrayList<>();
for (int i = 0; i < 5; i++) {if (i % 2 == 0) {continue;}
// Call target instance method in lambdaSupplier<List<String>> supplier = () -> target.getInnerRecords(i);result.addAll(supplier.get());}
// Overloading methodreturn process(target, "overload");}
public final Object process(TargetClass<T> target, String suffix) {// Override violation (intentional mismatch)OtherClass.process(target::getInnerValue);return target;}
class TypeDeclaration {}
}
private class TargetClass<T> extends ParentTarget<T> {T field;
protected List<String> getInnerRecords(int count) {List<String> list = new ArrayList<>();Runnable r = new Runnable() {public void run() {for (int i = 0; i < count; i++) {list.add(field.toString() + "_" + i);}}};r.run();return list;}
String getInnerValue() {return field.toString();}}
class ParentTarget<T> {}
class OtherClass {static <T> void process(Supplier<String> supplier) {System.out.println(supplier.get());}}