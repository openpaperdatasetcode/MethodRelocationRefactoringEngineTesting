package source;
import target.TargetClass;import java.util.List;import java.util.ArrayList;import java.io.IOException;
class SourceClass<T> {// First anonymous inner classprivate Runnable runnable1 = new Runnable() {@Overridepublic void run() {}};
// Second anonymous inner classprivate Runnable runnable2 = new Runnable() {@Overridepublic void run() {}};
class SourceInner {public List<String> innerMethod() {return new ArrayList<>();}}
// Overloading method 1public void methodToMove(TargetClass<T> target) throws IOException { // requires_try_catch// Constructor invocationSourceInner inner = new SourceInner();TargetClass<T>.TargetInner targetInner = target.new TargetInner();
// Variable callT var = target.targetField;
// Expression statementtarget.targetField = (T) "updated";
// Requires try-catchtry {if (var == null) throw new IOException("Null target field");} catch (IOException e) {throw e;}}
// Overloading method 2 (overload_exist)public void methodToMove(TargetClass<T> target, String arg) throws IOException { // requires_try_catch// Constructor invocation with call_method in parameter listSourceInner inner = new SourceInner();List<String> paramList = this.new SourceInner().innerMethod(); // outerInstance.new InnerClass().methodName()
// Variable callT var = target.targetField;
// Expression statementtarget.targetField = (T) arg;
// Requires try-catchtry {paramList.add(var.toString());} catch (NullPointerException e) {throw new IOException("Processing failed", e);}}
// Call_method: normal methodprotected List<String> innerClassMethod() {return new SourceInner().innerMethod();}}
package target;
protected class TargetClass<T> {T targetField;
class TargetInner {} // target_inner
public void example() {class LocalInner {} // target_feature: local inner class}}
