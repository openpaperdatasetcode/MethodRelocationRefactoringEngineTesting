package test;
protected record SourceRecord<T>(T value) {private String outerPrivate = "private_data";
public class MemberInner {static {// Synchronized varargs method in static code block (1 occurrence)Object result = new InnerHelper().combine("init", "data");}
private static class InnerHelper {// Synchronized varargs method with super.method()synchronized Object combine(String... parts) {super.toString();return String.join("", parts);}}
// Static method in source_innerObject process(TargetRecord<T>.StaticNested.Inner targetInner) {// Local inner classclass TargetProcessor {Object handle(TargetRecord<T>.StaticNested.Inner inner) {return inner.value;}}TargetProcessor processor = new TargetProcessor();
// Constructor invocationTargetRecord<T> target = new TargetRecord<>(value);// Super constructor invocationclass SubTarget<T> extends TargetRecord<T> {SubTarget(T val) {super(val);}}SubTarget<T> subTarget = new SubTarget<>(value);
// Type declaration statementTargetRecord<T>.StaticNested nested = new TargetRecord<T>.StaticNested();
// Private ExpressionStatement with 3 ClassName.field accessesprivate {String field1 = TargetRecord.StaticField; // 1String field2 = TargetRecord.StaticNested.NestedField; // 2String field3 = TargetRecord.StaticField + field2; // 3}
// Variable call - access target inner's fieldObject data = targetInner.value;
// Access outer private fieldtargetInner.value = (T) (outerPrivate + "_modified");
// Overloaded methods existoverloadMethod(targetInner);overloadMethod(targetInner, 1);
// Depends on static fieldString combined = TargetRecord.StaticField + targetInner.value;
return processor.handle(targetInner);}
// Overloaded methodsvoid overloadMethod(TargetRecord<T>.StaticNested.Inner inner) {}void overloadMethod(TargetRecord<T>.StaticNested.Inner inner, int code) {}}}
final record TargetRecord<T>(T content) {public static final String StaticField = "static_val";
public static class StaticNested {public static final String NestedField = "nested_static";
public class Inner {public T value;}}}