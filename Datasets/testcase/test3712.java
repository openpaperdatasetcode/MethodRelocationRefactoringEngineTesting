import java.lang.reflect.Method;import java.util.Objects;
interface SourceInterface extends ParentInterface {default int normalMethod(TargetInterface target, int depth) {if (depth <= 0) {return 0;}
class LocalInner {int process(TargetInterface t) {try {Method targetMethod = TargetInterface.StaticNested.class.getMethod("getCount");int reflectedVal = (int) targetMethod.invoke(null);
TargetInterface.StaticNested nested = new TargetInterface.StaticNested();nested.setValue(reflectedVal + depth);variableCall(nested);
return nested.getValue() + process(t, depth - 1);} catch (Exception e) {return 0;}}
int process(TargetInterface t, int recursiveDepth) {if (recursiveDepth <= 0) {return t.defaultCount();}return recursiveDepth + process(t, recursiveDepth - 1);}
private void variableCall(TargetInterface.StaticNested nested) {nested.increment();}}
LocalInner local = new LocalInner();int result = local.process(target);return result;}
static class StaticNested {public int callTargetInAssignment(TargetInterface target) {TargetInterface.StaticNested nested = new TargetInterface.StaticNested();nested.setCount(callTargetConstructor(target));return nested.getCount();}
private int callTargetConstructor(TargetInterface target) {TargetInterface.StaticNested newNested = new TargetInterface.StaticNested();return newNested.superMethod(target.defaultCount());}}}
interface ParentInterface {default int parentMethod() {return 10;}}
protected interface TargetInterface {default int defaultCount() {return 5;}
static class StaticNested {private int count;
public StaticNested() {super();}
public void setValue(int val) {this.count = val;}
public int getValue() {return count;}
public void increment() {count++;}
public void setCount(int c) {this.count = c;}
public int getCount() {return count;}
public int superMethod(int base) {return base * 2;}}}