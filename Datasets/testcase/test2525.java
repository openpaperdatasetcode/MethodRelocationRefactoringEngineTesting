package same;
private class SourceClass {private int privateField = 10;static class StaticNested {}
{Runnable anon = new Runnable() {public void run() {}};}
protected TargetClass createTarget() {TargetClass target = new TargetClass();TargetClass.StaticNested nested = target.new StaticNested();
nested.setValue(privateField + target.field);return target;}}
package same;
final class TargetClass {int field = 5;
static class StaticNested {private int value;
void setValue(int val) {this.value = val;}}}