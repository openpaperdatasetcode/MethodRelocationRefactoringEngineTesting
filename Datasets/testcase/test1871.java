package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
record SourceRecord(String data) extends SourceParent {// Member inner classpublic class SourceInner {final TargetClass<String>.Inner getInner(TargetClass<String> target) {return target.new Inner();}}
private void instanceMethod(TargetClass<String> target) throws ReflectiveOperationException {// SuperConstructorInvocation with volatile modifier and super.fieldvolatile TargetSubclass sub = new TargetSubclass(target.value(), super.parentField);
// Constructor invocation with call method in parameter listTargetClass<String>.Inner inner = target.new Inner(new SourceInner().getInner(target)::recursiveList);
// Enhanced for statementfor (String s : List.of("a", "b", "c")) {if (s.equals("b")) continue; // Continue statementtarget.value += s;}
// Variable callinner.append(target.value());
// Override violation (intentionally incorrect override)class BadOverride implements Runnable {@Overridepublic int run() { // Wrong return typereturn 0;}}
// Used by reflectionMethod method = TargetClass.class.getMethod("value");System.out.println(method.invoke(target));
// Requires throwsif (target.value().isEmpty()) {throw new ReflectiveOperationException("Empty value");}}
// Anonymous inner classprivate Runnable anonymous = new Runnable() {@Overridepublic void run() {new SourceRecord("anon").instanceMethod(new TargetClass<>("anon_data"));}};}
class SourceParent {protected String parentField = "parent_data";}
public record TargetClass<T>(T value) implements Processable {// Member inner classpublic class Inner {private List<String> data = new ArrayList<>();private final Provider provider;
public Inner(Provider provider) {this.provider = provider;}
public void append(T val) {data.add(val.toString());}
// Recursive methodpublic List<String> recursiveList() {if (data.size() > 3) return data;data.add("rec_" + data.size());return recursiveList();}
@FunctionalInterfacepublic interface Provider {List<String> get();}}
@Overridepublic void process() {}}
interface Processable {void process();}
class TargetSubclass extends TargetClass<String> {public TargetSubclass(String value, String parentField) {super(value + parentField);}}