package test;
package target;public class TargetClass {public static int staticField = 1;static class StaticNestedTarget {}
public TargetClass() {}
public TargetClass(int value) {}
public TargetClass instanceMethod(TargetClass param) {return param;}}abstract class SubTargetClass extends TargetClass {public abstract int subClassMethod(int num);}package test;
import target.TargetClass;import target.SubTargetClass;
class SourceClass {TargetClass instanceMethod () {super ();TargetClass rawTarget = new TargetClass ();
private void doLoop () {int count = 0;do {count += TargetClass.staticField; } while (count < 3);}doLoop ();
try {@MyAnnotation (attr = TargetClass.instanceMethod (rawTarget))TargetClass methodResult = TargetClass.instanceMethod (rawTarget);
SubTargetClass subTarget = new SubTargetClass () {@Overridepublic int subClassMethod (int num) {return num * 2;}};int subCallResult = subTarget.subClassMethod (5);
TargetClass newTarget = new TargetClass (subCallResult);return newTarget;} catch (Exception e) {return rawTarget;}}}