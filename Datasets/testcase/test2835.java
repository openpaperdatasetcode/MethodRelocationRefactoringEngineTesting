package test;
public enum SourceEnum<T> {INSTANCE1, INSTANCE2;
static class StaticNestedSource<T> {T value;}
private TargetEnum targetField = TargetEnum.VALUE1;
private TargetEnum methodToMove() {try {class LocalInner {void process() {// ExpressionStatement in inner class: obj.field and 2targetField.field = 2;System.out.println(targetField.field);
// Super constructor invocationsuper.getClass();}}
new LocalInner().process();
// Constructor invocationStaticNestedSource<Integer> nested = new StaticNestedSource<>();nested.value = targetField.field;
// Variable calltargetField.execute(nested.value);
return targetField;} catch (Exception e) {return TargetEnum.VALUE2;}}
private TargetEnum methodToMove(int param) {targetField.field = param + 2;return targetField;}}
enum TargetEnum implements Runnable {VALUE1, VALUE2;
int field;
@Overridepublic void run() {}
void execute(int value) {new Object() {{System.out.println("Anonymous inner class: " + value);}};}}