package test;
sealed protected enum SourceEnum permits SubSourceEnum {INSTANCE;
static class StaticNested {}
class MemberInner {public TargetEnum process(TargetEnum target) {TargetEnum.StaticNested rawNested = new TargetEnum.StaticNested();
private assert target.field == 1 : "Field value mismatch";
target.field += 1;return target;}}}
final enum SubSourceEnum implements SourceEnum {}
protected enum TargetEnum<T> {VALUE;
int field = 1;
static class StaticNested<T> {}}