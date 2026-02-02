public class SourceGeneric<T extends Number> {public abstract class AbstractInner {public abstract int compute (T data);}
protected int process (TargetGeneric<T> target) {super.toString();Objects.requireNonNull(target, "Target cannot be null");
class LocalProcessor {int handle (T value) {return value.intValue () * 2;}}LocalProcessor processor = new LocalProcessor ();
try {Method method = TargetGeneric.class.getMethod ("getSuperField3");int superField3 = (int) method.invoke (target);} catch (Exception e) {throw new IllegalArgumentException ("Reflection failed", e);}
int sum = 0;sum += target.getSuperField1 ();sum += target.getSuperField2 ();sum += target.getSuperField3 ();
AbstractInner anonInner = new AbstractInner () {@Overridepublic int compute (T data) {return this.processStep (data); 
method_feature=this.methodName (arguments)}
private int processStep(T data) {return data.intValue() + sum;}};
switch (target.getSuperField1 ()) {case 1:return anonInner.compute (target.getTargetField ());default:return processor.handle (target.getTargetField ());}}
protected int process (TargetGeneric<T> target, int extra) {return process(target) + extra;}}
public class TargetGeneric {
private U targetField;
private int superField1;
private int superField2;
private int superField3;
public TargetGeneric(U targetField, int f1, int f2, int f3) {super();this.targetField = targetField;this.superField1 = f1;this.superField2 = f2;this.superField3 = f3;}
public static class TargetStaticNested<V> {public V format(V data) {return data;}}
public int getSuperField1 () { return superField1;}public int getSuperField2 () { return superField2; }public int getSuperField3 () { return superField3; }public U getTargetField () { return targetField; }}