package test;
import java.lang.reflect.Method;
abstract class ParentGenericClass<T> {public abstract Object abstractParentMethod(T data);}
public class SourceGenericClass<T> {private TargetGenericClass<T> targetField = new TargetGenericClass<>();
class FirstSourceInner {T getInnerData () {return (T) "firstInnerData";}}
class SecondSourceInner {TargetGenericClass<T>.TargetInnerRec getTargetInnerRec() {return targetField.new TargetInnerRec();}}
private int instanceMethod (T data) {
try {Method reflectMethod = SourceGenericClass.class.getDeclaredMethod ("instanceMethod", Object.class);reflectMethod.setAccessible (true);reflectMethod.invoke (this, data);} catch (Exception e) {e.printStackTrace ();}
FirstSourceInner firstInner = new FirstSourceInner ();SecondSourceInner secondInner = new SecondSourceInner ();TargetGenericClass<T>.TargetInnerRec targetInnerRec = secondInner.getTargetInnerRec();T varCall1 = firstInner.getInnerData();T varCall2 = targetInnerRec.getRecData();
switch (varCall1.toString ().length ()) {case 5:targetField.parentMethod (varCall1);break;default:targetField.parentMethod (varCall2);}} catch (Exception e) {}
if (targetField instanceof ParentGenericClass) {throw new RuntimeException ("Override violation: Abstract method not implemented");}
return varCall1.toString().length() + varCall2.toString().length();}}
public class TargetGenericClass<T> extends ParentGenericClass<T> {
class TargetInnerRec {T getRecData () {return (T) "targetInnerRecData";}}
public TargetGenericClass () {
super ();
class TargetLocalInner {T getLocalData() {return (T) "localInnerData";}}new TargetLocalInner().getLocalData();}
public void parentMethod (T data) {super.abstractParentMethod (data);}
@Overridepublic Object abstractParentMethod (T data) {throw new UnsupportedOperationException ("Override violation: Method not implemented");}}