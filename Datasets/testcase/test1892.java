package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
sealed abstract class SourceClass permits SourceSubclass {// Static nested classpublic static class SourceStatic {public String staticField = "static_data";}
// Member inner class containing the target methodpublic class SourceInner {public TargetClass normalMethod(TargetClass.Inner targetInner) {TargetClass result = new TargetClass() {@Overridepublic void abstractMethod() {}};
// Variable callresult.setValue(targetInner.getData());
// For statementfor (int i = 0; i < targetInner.getId(); i++) {result.addLog("Iteration: " + i);}
// Overload exists (two versions of processVarargs)result = processVarargs(targetInner);result = processVarargs(targetInner, "extra");
// Expression with varargs source method (this call)TargetClass processed = this.processVarargs(targetInner, "param1", "param2");result.merge(processed);
// Used by reflectiontry {Method method = TargetClass.Inner.class.getMethod("getData");result.setValue((String) method.invoke(targetInner));} catch (Exception e) {// No new exception}
// Same package others with VariableDeclarationStatement (this.field)OtherHelper helper = new OtherHelper();result = helper.enhance(result);
return result;}
// Varargs method (overload 1)public TargetClass processVarargs(TargetClass.Inner... inners) {TargetClass target = new TargetClass() {@Overridepublic void abstractMethod() {}};for (TargetClass.Inner inner : inners) {target.setValue(inner.getData());}return target;}
// Overloaded varargs method (overload 2)public TargetClass processVarargs(TargetClass.Inner inner, String... extras) {TargetClass target = processVarargs(inner);for (String extra : extras) {target.addLog(extra);}return target;}}}
final class SourceSubclass extends SourceClass {}
protected abstract class TargetClass extends TargetParent {private String value;private List<String> logs = new ArrayList<>();
public void setValue(String value) {this.value = value;}
public void addLog(String log) {logs.add(log);}
public void merge(TargetClass other) {this.logs.addAll(other.logs);}
// Member inner classpublic class Inner {private int id;private String data;
public Inner(int id, String data) {this.id = id;this.data = data;}
public int getId() {return id;}
public String getData() {return data;}}
public abstract void abstractMethod();}
class TargetParent {protected String parentField = "parent_value";}
class OtherHelper {public TargetClass enhance(TargetClass target) {// VariableDeclarationStatement with 1 this.field referenceclass Enhancer {private String suffix = "_enhanced";
public TargetClass process(TargetClass t) {public String processedValue = this.suffix; // this.field referencet.setValue(t.value + processedValue);return t;}}return new Enhancer().process(target);}}
