package test;
import java.util.function.Supplier;
public class SourceClass<T> {public strictfp TargetClass<T> process(TargetClass<T>.StaticNested nested) {// Local inner classclass LocalHandler {T handle(T value) {return value;}}LocalHandler handler = new LocalHandler();
// Anonymous inner classSupplier<T> supplier = new Supplier<T>() {@Overridepublic T get() {return nested.value;}};
// Super keywordsSystem.out.println(super.toString());
// Variable call - access target's static nested class fieldT data = nested.value;
// Depends on static fieldif (TargetClass.staticFlag) {data = handler.handle(data);}
// If statementif (data == null) {data = (T) "default";}
// SuperMethodReference (2 occurrences)Runnable r1 = nested::superToString;Runnable r2 = new TargetClass<T>()::superHashCode;
TargetClass<T> target = new TargetClass<>();target.new StaticNested().value = data;return target;}}
public class TargetClass {
public static boolean staticFlag = true;
public static class StaticNested {public U value;
public String superToString() {return super.toString();}}
public int superHashCode() {return super.hashCode();}}