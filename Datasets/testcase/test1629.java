package test;
import java.io.IOException;import java.util.ArrayList;import java.util.List;
sealed class SourceClass<T> permits ExtendedSourceClass<T> {private T outerPrivate = (T) "private_data";
public final Object process(TargetClass<T> target) throws IOException {List<Object> results = new ArrayList<>();
// Anonymous inner classRunnable init = new Runnable() {@Overridepublic void run() {target.data = outerPrivate;}};init.run();
// Local inner classclass TargetProcessor {Object handle(TargetClass<T> t) {return t.data;}}TargetProcessor processor = new TargetProcessor();
// Protected EmptyStatement with 2 this.field accesses (same_package_target)protected {this.field1 = target.data; ;this.field2 = target.data; ;}T field1;T field2;
// Empty statement;
// Enhanced for statementfor (T item : target.items) {results.add(item);}
// Expression statementtarget.data = (T) "updated";
// Variable call - access target's fieldresults.add(target.data);
// Access outer private fieldtarget.items.add(outerPrivate);
// Overloading methods in for loop (2 occurrences)for (int i = 0; i < 2; i++) {if (i == 0) {results.add(this.new InnerHelper().overloadMethod(target));} else {results.add(this.new InnerHelper().overloadMethod(target, i));}}
// Overloaded methods existoverloadCheck(target);overloadCheck(target, "check");
// Requires throwsif (target.items.isEmpty()) {throw new IOException("Items list is empty");}
return processor.handle(target);}
public class InnerHelper {public Object overloadMethod(TargetClass<T> target) {return target.data;}
public Object overloadMethod(TargetClass<T> target, int index) {return target.items.get(index);}}
private void overloadCheck(TargetClass<T> target) {}private void overloadCheck(TargetClass<T> target, String msg) {}}
final class ExtendedSourceClass<T> extends SourceClass<T> {}
private class TargetClass<T> {public T data;public List<T> items = new ArrayList<>();}