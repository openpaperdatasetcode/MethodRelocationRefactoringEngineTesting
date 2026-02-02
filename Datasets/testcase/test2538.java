package same;
abstract class SourceClass {protected int outerProtected = 20;
{Runnable anon1 = new Runnable() { public void run() {} };Runnable anon2 = new Runnable() { public void run() {} };}
class InnerRec<T extends Number> {@SuppressWarnings("unchecked")private void process(TargetClass.InnerRec inner) {protected int current = inner.dataField;current += outerProtected;
TargetClass.StaticNested<T> nested = new TargetClass.StaticNested<>();nested.setValue((T) Integer.valueOf(current));}}}
package same;
strictfp abstract class TargetClass {static class StaticNested<V> {private V value;void setValue(V val) { this.value = val; }}
class InnerRec {int dataField = 10;}
abstract void execute();}