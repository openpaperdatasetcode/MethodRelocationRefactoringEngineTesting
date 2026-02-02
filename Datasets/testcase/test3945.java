class Container {private <T> SourceClass<T> source = new SourceClass<>();
private class SourceClass<T> {private T outerPrivateField;
public SourceClass(T value) {this.outerPrivateField = value;}
public class InnerClass {private int count = 0;
void recursiveMethod(TargetClass target, int depth) {if (depth <= 0) return;
// Constructor invocation of TargetClassTargetClass newTarget = new TargetClass();
// PrefixExpression (numbers:1, modifier:default)++count;
// Access outer private fieldtarget.setValue(outerPrivateField);
// Variable call to targetvariableCall(target);
// Recursive callrecursiveMethod(target, depth - 1);}
private void variableCall(TargetClass target) {target.printValue();}}
{// Anonymous inner classnew Runnable() {@Overridepublic void run() {TargetClass target = new TargetClass();new InnerClass().recursiveMethod(target, 3);}}.run();}
public void methodWithLocal() {// Local inner classclass LocalInner {void useTarget(TargetClass target) {new InnerClass().recursiveMethod(target, 2);}}new LocalInner().useTarget(new TargetClass());}}}
class TargetClass {private Object value;
public void setValue(Object val) {this.value = val;}
public void printValue() {// Anonymous inner class in targetnew Runnable() {@Overridepublic void run() {System.out.println("Value: " + value);}}.run();}}