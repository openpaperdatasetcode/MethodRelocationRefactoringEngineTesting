package test;
import other.DiffPackageClass;import java.util.function.Consumer;
class SourceClass<T> {static class StaticNested {protected Object helperMethod() {return new Object();}}
public TargetClass<T> methodToMove(TargetClass<T> targetParam) {TargetClass<T> newTarget = new TargetClass<>();DiffPackageClass other = new DiffPackageClass();
int i = 0;while (targetParam.field == null) {other.process();i++;if (i > 5) break;}
if (targetParam != null) {Object result = SourceClass.StaticNested.helperMethod();}
System.out.println(super.toString());targetParam.variableCall();
int j = 0;do {targetParam.new InnerClass<T>().process();j++;} while (j < 3);
Consumer<T> consumer = new Consumer<>() {public void accept(T t) {}};
return newTarget;}}
public class TargetClass<T> {Object field;
class InnerClass<S> {protected void process() {}}
void variableCall() {}}
package other;
public class DiffPackageClass {public void process() {}}