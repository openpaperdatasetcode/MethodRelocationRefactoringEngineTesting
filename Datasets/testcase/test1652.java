package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Function;
/**
A generic record class containing static nested classes
and a static method to process private target records.
@param <T> The type of content stored in the record*/public record SourceRecord<T>(T content) {// First static nested class with type parameterpublic static class StaticHelper1 {
public static final String STATIC_FIELD = "helper1_static";
}
// Second static nested classpublic static class StaticHelper2 {public static <V> List<V> createList(V... elements) {List<V> list = new ArrayList<>();for (V elem : elements) {list.add(elem);}return list;}}
static {// Static code block with 1 protected target instance method (method reference)Function<PrivateTarget<String>, List<String>> processor = PrivateTarget::processData;List<String> processed = processor.apply(new PrivateTarget<>("init", "base"));}
/**
Static method to create and process PrivateTarget instances
@param data The core data for the target record
@param base The base value from parent class
@param <T> The type of data
@return Processed PrivateTarget instance*/static <T> PrivateTarget<T> processTarget(T data, String base) {// Create target record instancePrivateTarget<T> target = new PrivateTarget<>(data, base);
// Variable callList<String> baseInfo = target.getBaseInfo();System.out.println("Base info: " + baseInfo);
// Super keywords usageString parentData = target.superBase();System.out.println("Parent data: " + parentData);
// Depends on static fieldif (base.contains(StaticHelper1.STATIC_FIELD)) {target = new PrivateTarget<>(data, base + "_modified");}
// For loop with inner_class method callfor (int i = 0; i < 2; i++) {target = PrivateTarget.InnerProcessor.process(target, "suffix_" + i);}
return target;}}
/**
Private target record extending a base class
@param <T> The type of data stored in the record/
private record PrivateTarget<T>(T data, String base) extends TargetParent {
// Member inner class
public static class InnerProcessor {
/*
Processes target record with a suffix
@param target The target record to process
@param suffix The suffix to append
@param <T> The type of target data
@return Processed target record
*/
public static <T> PrivateTarget<T> process(PrivateTarget<T> target, String suffix) {
return new PrivateTarget<>(target.data, target.base + "_" + suffix);
}
}
/**
Protected instance method to process data into list
@return List of processed data strings
*/
protected List<String> processData() {
List<String> result = new ArrayList<>();
result.add(data.toString());
result.add(base);
return result;
}
/**
Accesses parent class method using super
@return Parent class base value
*/
public String superBase() {
return super.parentBase;
}
}
/**
Parent class for target record
*/
class TargetParent {
protected String parentBase = "parent_base_value";
}