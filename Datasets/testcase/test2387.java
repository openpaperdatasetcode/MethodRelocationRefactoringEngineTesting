package source;
import target.TargetClass;
protected class SourceClass {TargetClass targetField;
class Inner {class RecursiveInner {/**
Javadoc for the method
*/
public Object moveMethod() {
new TargetClass();
if (targetField != null) {
synchronized (this) {
targetField.doSomething();
}
}
TargetClass<String> bounded = new TargetClass<>();
return targetField.instanceVar;
}
}
}
}
package target;
public class TargetClass<T extends CharSequence> {Object instanceVar;
static {TargetClass type = new TargetClass().staticMethod();}
static TargetClass staticMethod() {return new TargetClass();}
void doSomething() {new Runnable() {public void run() {}};}}