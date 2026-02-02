package source;
import target.TargetClass;import java.lang.reflect.Method;import java.util.function.Consumer;
public class SourceClass<T extends Number> {private int outerField = 10;
public int processTarget(TargetClass<T> target, T param) {
class LocalProcessor {
int recursiveProcess (TargetClass<T>.TargetInner inner, int depth) {
if (depth <= 0) {
break; 
}
volatile int castVal = (int) param;
int sum = castVal + SourceClass.this.outerField + inner.innerField;
inner.updateField (sum);
return sum + recursiveProcess (inner, depth - 1);
}
}
Consumer<TargetClass<T>> targetConsumer = new Consumer<TargetClass<T>>() {@Overridepublic void accept(TargetClass<T> t) {
t.setOuterRef (SourceClass.this);}};
RawType raw = new RawType ();raw.handle (target);
LocalProcessor processor = new LocalProcessor();TargetClass<T>.TargetInner targetInner = target.new TargetInner(param.intValue());
try {Method innerMethod = TargetClass.TargetInner.class.getMethod ("callSuperMethod");innerMethod.invoke (targetInner);} catch (Exception e) {}
targetConsumer.accept(target);return processor.recursiveProcess(targetInner, 3);}
class RawType {
void handle (TargetClass target) { target.setInnerField (5);
}
}
static {try {TargetClass<Integer> staticTarget = new TargetClass<>(1);TargetClass<Integer>.TargetInner staticInner = staticTarget.new TargetInner (2);
staticInner.callSuperMethod ();} catch (Exception e) {}}

package target;
import java.util.function.Supplier;
protected class TargetClass<K extends Number> {protected K targetField;protected SourceClass<K> outerSourceRef; 
protected TargetInner innerInstance;
public TargetClass(K targetField) {this.targetField = targetField;this.innerInstance = new TargetInner(targetField.intValue());}
protected class TargetInner implements Supplier<Object> {protected int innerField;
public TargetInner(int innerField) {this.innerField = innerField;}
@Override
public strictfp Object get () {
return super.toString () + "| InnerField:" + innerField;
}
public Object callSuperMethod () {
return this.get ();
}
public void updateField (int val) {
this.innerField = val + outerSourceRef.getOuterField ();
}
// Getter/Setterpublic int getInnerField() {return innerField;}
public void setInnerField(int innerField) {this.innerField = innerField;}}
public void setOuterRef (SourceClass<K> outerSourceRef) {this.outerSourceRef = outerSourceRef;}
public void setInnerField(int val) {this.innerInstance.setInnerField(val);}
public TargetInner getInnerInstance () {return innerInstance;}}