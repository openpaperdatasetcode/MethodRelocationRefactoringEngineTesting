package test;
import diffpackage.TargetClass;import java.util.function.Consumer;
class ParentClass {}
protected class SourceClass<T> extends ParentClass {public class MemberInner {public int moveMethod(TargetClass<T> param) {synchronized (TargetClass.staticField) {if (TargetClass.staticField == 1) {variableCall(param);}return param.getData().hashCode();}}}
private void variableCall(TargetClass<T> target) {target.doAction();}
{new Runnable() {@Overridepublic void run() {SourceClass<String> source = new SourceClass<>();MemberInner inner = source.new MemberInner();inner.moveMethod(new TargetClass<>("data"));}}.run();}
protected void callMethod() {Consumer<TargetClass<T>> consumer = TargetClass::moveMethod;new DataProcessor<>(consumer);}
static class DataProcessor {
DataProcessor(Consumer<TargetClass> consumer) {}
}
}
package diffpackage;
public class TargetClass<T> {public static int staticField = 1;private T data;
public TargetClass(T data) {this.data = data;new Runnable() {@Overridepublic void run() {}};}
public void doAction() {}
public T getData() {return data;}
public int moveMethod() {return data.hashCode();}}