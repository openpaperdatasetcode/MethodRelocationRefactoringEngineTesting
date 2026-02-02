package test;
public enum SourceEnum {INSTANCE;
public static class StaticNested {private Object methodToMove(TargetEnum<?> target) throws Exception {int localVar;do {localVar = 0;synchronized (this) {localVar++;}LocalInner localInner = new LocalInner();target.memberInner.field = localVar;Object obj = new Object();return obj;} while (localVar < 5);return null;}
private class LocalInner {}}
private Object methodToMove(int x) { return null; }}
final enum TargetEnum<T> {INSTANCE;
MemberInner memberInner = new MemberInner();
class MemberInner {int field;}}