package same;
public enum SourceEnum {VALUE1 {{Runnable anon1 = new Runnable() {public void run() {}};}},VALUE2 {{Runnable anon2 = new Runnable() {public void run() {}};}};
private int privateField = 5;
TargetEnum.InnerRec getTargetInner(TargetEnum target) {class LocalType {}LocalType local = new LocalType();
target.counter++;TargetEnum.InnerRec inner = target.new InnerRec();inner.value = privateField + TargetEnum.STATIC_FIELD;
return inner;}}
package same;
protected enum TargetEnum {INSTANCE {{Runnable anon = new Runnable() {public void run() {}};}};
static int STATIC_FIELD = 10;int counter = 0;
class InnerRec {class NestedInner {}int value;}}