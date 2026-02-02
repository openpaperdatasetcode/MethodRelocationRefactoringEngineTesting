package source;
import target.Target;import java.util.ArrayList;import java.util.List;
protected class Source<T> {static class StaticNested {
U value;
StaticNested(U value) {this.value = value;}}
protected Object process(Target<String> target) {// Constructor invocationTarget.Nested<Integer> targetNested = new Target.Nested<>(100);StaticNested<T> sourceNested = new StaticNested<>(null);
// Anonymous inner classRunnable task = new Runnable() {@Overridepublic void run() {target.setData("anonymous_data");}};task.run();
// Empty statement;
List<Object> results = new ArrayList<>();int count = 0;
// While statementwhile (count < 3) {// Variable call (access target field)results.add(target.getData());count++;}
// 2 SuperFieldAccess expressionsresults.add(target.superField1);results.add(target.superField2);
// Return statementif (results.isEmpty()) {return null;}
return results;}}
package target;
public class SuperTarget {protected String superField1 = "super1";protected int superField2 = 200;}
class Target<T> extends SuperTarget {private T data;
public T getData() {return data;}
public void setData(T data) {this.data = data;}
static class Nested {
U nestedData;
Nested(U data) {this.nestedData = data;}}}