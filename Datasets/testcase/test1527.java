package test;
import java.util.List;import java.util.ArrayList;import java.io.IOException;import java.lang.reflect.Method;
abstract class SuperType {public abstract List<String> fetchData(String... params);}
class OtherClass extends SuperType {@Overridepublic List<String> fetchData(String... params) {List<String> result = new ArrayList<>();for (String param : params) {result.add(param);}return result;}}
private class Target<T> {private T value;
public Target(T value) {this.value = value;}
static class Nested {
private U nestedValue;
public Nested(U value) {this.nestedValue = value;}
public U getValue() {return nestedValue;}
public static <V> Target<V> create(V data) {return new Target<>(data);}}
public T getValue() {return value;}}
public class Source {private String outerPrivate = "outer_private_data";
static class StaticNested {static {// Static code block with call_method (superTypeReference.methodName)SuperType superRef = new OtherClass();List<String> staticData = superRef.fetchData("static_init");}}
class MemberInner {class InnerRec {// Method in source_inner_rec positionpublic final void process(Target<String> target, String... args) throws IOException {// Super constructor invocation (indirect via outer context)super();
// Uses outer thisSource.this.outerPrivate = "modified_by_inner";
// Access outer private fieldString privateData = Source.this.outerPrivate;
// Synchronized statementsynchronized (this) {target.getValue();}
// Instance code block with varargs method (1){Target.Nested<String> nested = Target.Nested.create("instance_block");Target<String> createdTarget = Target.Nested::create;}
// Variable callTarget.Nested<String> targetNested = new Target.Nested<>(target.getValue());String nestedValue = targetNested.getValue();
// MethodInvocation (1)final List<String> dataList = new ArrayList<>();dataList.add(nestedValue);
// Requires throws (IOException)if (args.length == 0) {throw new IOException("No arguments provided");}
// IOException handlingtry {if (target.getValue() == null) {throw new IOException("Target value is null");}} catch (IOException e) {throw e; // Re-throw as required}}}}}