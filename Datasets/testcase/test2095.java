package test;
class ParentEnum {}
protected enum SourceEnum extends ParentEnum {INSTANCE;
static class StaticNested {}
class FirstInner {class InnerRecursive {public final Object methodToMove(TargetEnum... targets) {class LocalType {}LocalType local = new LocalType();
super();new ParentEnum();
assert TargetEnum.FIELD1 > 0;assert !TargetEnum.FIELD2.isEmpty();assert TargetEnum.FIELD3 != null;
TargetEnum.StaticNested nested = new TargetEnum.StaticNested();nested.value = ParentEnum.super.recursiveMethod();
for (TargetEnum target : targets) {target.variableCall();target.accessInstanceMethod();}
String expr = "processed";return expr;}}}}
enum TargetEnum {VALUE1, VALUE2;
static int FIELD1;static String FIELD2;static Object FIELD3;
static class StaticNested {TargetEnum value;}
void variableCall() {}
void accessInstanceMethod() {}}
class ParentEnum {public TargetEnum recursiveMethod() {return TargetEnum.VALUE1;}}