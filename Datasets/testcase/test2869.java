package test;
public enum SourceEnum<T> {INSTANCE;
static class StaticNested {}
class MemberInner {}
private void methodToMove(TargetEnum.TargetInner target) {// Raw type usageRawType rawObj = new RawType();
// Labeled statementloopLabel: do {// This method callthis.utilMethod();// Variable callint val = target.getCount();// Uses outer thisSourceEnum.this.toString();
if (val > 5) {break loopLabel;}} while (true);}
// Override violation (method with same signature as parent's public method)@Overridepublic String toString() {return super.toString();}
private void utilMethod() {}}
enum TargetEnum extends SuperClass {INSTANCE;
static class StaticNested {}
class TargetInner {private int count;
// Accessor methodpublic int getCount() { return count; }public void setCount(int count) { this.count = count; }}
int callMethod() {TargetInner inner = new TargetInner();// Property assignment with target call methodinner.setCount(TargetEnum.StaticNested.staticAccessorMethod());return inner.getCount();}
static class StaticNested {static int staticAccessorMethod() {return 3;}}}
class SuperClass {}class RawType {}