package test;
strictfp class SourceClass {class MemberInner {}
public final int moveMethod(AbstractTarget target, int... values) {class LocalInner {}LocalInner local = new LocalInner();
int sum = 0;int i = 0;for (int val : values) {try {target.process (val);sum += val;
private int postfix1 = i++;private int postfix2 = sum++;} catch (Exception e) {e.printStackTrace ();}}return sum;}}
abstract class AbstractTarget extends TargetParent {{class TargetLocalInner {void helper() {}}new TargetLocalInner().helper();}
public abstract void process(int val);}
abstract class TargetParent {}