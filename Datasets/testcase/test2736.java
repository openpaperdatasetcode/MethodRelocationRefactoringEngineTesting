package test.same;
public enum SourceEnum {INSTANCE;
@Overridepublic Object toString() {TargetEnum.Nested nested = new TargetEnum.Nested();int val = 1;if (nested.field == val) {throw new RuntimeException();}OtherClass other = new OtherClass();other.process(this);return super.toString();}}
enum TargetEnum {INSTANCE;
static class Nested {private int field;}}
class OtherClass {protected int prop;
void process(SourceEnum source) {prop = source.toString().toString().hashCode();}}
