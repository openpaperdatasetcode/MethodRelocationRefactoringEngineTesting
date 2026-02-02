package test;
import java.util.ArrayList;import java.util.List;import java.io.IOException;
interface Processable {int validate();}
private class SourceClass<T> implements Processable {public static class StaticNested {public static final String STATIC_FIELD = "static_value";}
@Overridepublic int validate() {return 0;}
public List<String> process(TargetClass.Inner... inners) throws IOException {List<String> result = new ArrayList<>();int count = 0;
// Local inner class (subclass overriding)class SubProcessor extends SourceClass<T> {// Overriding method (1 occurrence, sub_class, this.method())@Overridepublic int validate() {try {this.check();return 1;} catch (Exception e) {return -1;}}
private void check() {}}SubProcessor processor = new SubProcessor();
for (TargetClass.Inner inner : inners) {count++;if (count > 3) {// Break statementbreak;}
// Super constructor invocation in target innerTargetClass.Inner newInner = inner.new Inner();
// Variable call - access target inner's fieldresult.add(inner.data);
// Access instance field of target innerinner.counter++;
// Depends on static fieldresult.add(StaticNested.STATIC_FIELD + "_" + inner.counter);
// Override violation (if target method is final)inner = new TargetClass.Inner() {@Overridepublic void finalMethod() {// Compile error if finalMethod() is final}};
// Exception handling with overriding methodif (processor.validate() < 0) {throw new IOException("Validation failed"); // requires_throws}}
return result;}}
public class TargetClass {public class Inner {public String data;public int counter;
public Inner() {super(); // Super constructor invocation}
public final void finalMethod() {}}}