package test.same;
public enum SourceEnum {INSTANCE;
static class StaticNested<T extends Number> {}
void overloadingMethod(TargetEnum target) {class LocalInner {<T extends Number> void process(T val) {Object var = target.nested.field;}}LocalInner local = new LocalInner();
try {TargetEnum.StaticNested nested = target.nested;Object var = nested.field;local.process((Number) var);} catch (ClassCastException e) {}
TargetEnum result = (target == TargetEnum.VALUE)? SourceEnum.createTarget(1): SourceEnum.createTarget("default");
if (result == null) {throw new IllegalArgumentException(SourceEnum.this.name());}}
void overloadingMethod(TargetEnum.StaticNested nested) {Object var = nested.field;}
final static TargetEnum createTarget(int code) {return TargetEnum.VALUE;}
final static TargetEnum createTarget(String name) {return TargetEnum.OTHER;}}
enum TargetEnum {VALUE, OTHER;
static class StaticNested {Object field;}
StaticNested nested = new StaticNested();}