package same;
private class SourceClass {private int outerField = 20;
class MemberInner {}
{Runnable anon = new Runnable() {public void run() {}};}
/**
Processes target with varargs parameters
@param target the target class instance
@param args variable arguments
@return processed object*/private Object process(TargetClass target, Object... args) {class LocalType {}LocalType local = new LocalType();
// Empty statements with target fieldstarget.inner.field1 = 10; ;target.inner.field2 = 20; ;target.inner.field3 = 30; ;
synchronized (target) {switch (args.length) {case 0:return target.inner.field1 + outerField;case 1:return target.inner.field2 * SourceClass.this.outerField;default:return super.toString() + target.inner.field3;}}}}
package same;
class TargetClass {Inner inner = new Inner();
class Inner {int field1;int field2;int field3;}}
