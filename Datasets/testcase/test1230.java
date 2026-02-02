package test.pkg;
import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
public class SourceClass {private TargetClass<String> targetInstance = new TargetClass<>();
private List<String> methodToMove() {List<String> result = new ArrayList<>();
// Raw type usageTargetClass rawTarget = new TargetClass();result.add(rawTarget.getTargetField());
// Access target's field (per_condition)result.add(targetInstance.getTargetField());
// Lambda expression containing parent class accessor callSupplier<List<String>> supplier = () -> {ParentClass parent = new ParentClass();// Accessor method call (1: count, parent_class: owner, accessor: type)return parent.getParentList("lambdaArg");};result.addAll(supplier.get());
// First anonymous inner classRunnable runnable1 = new Runnable() {@Overridepublic void run() {result.add("anonymous1: " + targetInstance.getTargetField());}};runnable1.run();
// Second anonymous inner classSupplier<String> supplier2 = new Supplier<String>() {@Overridepublic String get() {return "anonymous2: " + rawTarget.getTargetField();}};result.add(supplier2.get());
// Variable call (target's local inner class method)TargetClass.LocalInnerClass localInner = targetInstance.new LocalInnerClass();result.add(localInner.innerMethod("varCallArg"));
return result;}}
public class TargetClass<T> {private T targetField = (T) "targetValue";
public T getTargetField() {return targetField;}
public void outerMethod() {// Local inner class (target_feature)class LocalInnerClass {public String innerMethod(String arg) {return "localInner: " + arg;}}LocalInnerClass local = new LocalInnerClass();local.innerMethod("test");}
// Method will be moved here:// private List<String> methodToMove() { ... }}
class ParentClass {// Parent class accessor method (returns List<String>)public List<String> getParentList(String arg) {List<String> list = new ArrayList<>();list.add("parent: " + arg);return list;}
}