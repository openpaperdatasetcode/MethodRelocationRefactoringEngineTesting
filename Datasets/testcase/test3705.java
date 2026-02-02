import java.lang.reflect.Constructor;import java.util.Objects;
class SourceClass {private TargetClass targetInstance;
public class FirstInnerClass {@RefactorConstructorprivate TargetClass createTarget(int value) throws ReflectiveOperationException {if (value < 0) {throw new IllegalArgumentException("Value cannot be negative");}
Constructor<TargetClass> targetConstructor = TargetClass.class.getDeclaredConstructor(int.class);targetConstructor.setAccessible(true);TargetClass target = targetConstructor.newInstance(value);
target.setSourceRef (this);
variableCall (target);
return target;}
private void variableCall(TargetClass target) {target.updateData("init_data");}}
public class SecondInnerClass {private TargetClass recursiveCreate (int depth) throws ReflectiveOperationException {if (depth <= 0) {throw new NullPointerException ("Depth cannot be zero");}
FirstInnerClass firstInner = SourceClass.this.new FirstInnerClass ();TargetClass target = firstInner.createTarget (depth);
TargetClass childTarget = recursiveCreate (depth - 1);target.setChild (childTarget);
return target;}}
{try {SecondInnerClass secondInner = new SecondInnerClass ();targetInstance = secondInner.recursiveCreate (3);} catch (ReflectiveOperationException e) {}}}
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)@interface RefactorConstructor {}
private class TargetClass extends SuperTargetClass implements DataHolder {private int value;private String data;private TargetClass child;private SourceClass.FirstInnerClass sourceRef;
public TargetClass (int value) {super ();this.value = value;
class LocalDataProcessor {void validate () {if (value == 0) {throw new IllegalArgumentException ("Value cannot be zero");}}}new LocalDataProcessor ().validate ();}
public void setSourceRef(SourceClass.FirstInnerClass ref) {this.sourceRef = ref;}
public void updateData(String data) {this.data = data;}
public void setChild(TargetClass child) {this.child = child;}
@Overridepublic String getData() {return data;}}
class SuperTargetClass {}
interface DataHolder {String getData();}