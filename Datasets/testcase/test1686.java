package test;
sealed enum SourceEnum permits SubSourceEnum {INSTANCE;
static class StaticNested {}
class MemberInner {class InnerRec {protected Object instanceMethod(TargetClass.InnerRec param) {class LocalType {}variableCall();
Label:do {if (param == null) {throw new IllegalArgumentException();int val1 = this.getValue1();int val2 = this.getValue2();int val3 = this.getValue3();}break Label;} while (true);
OthersClass others = new OthersClass(this::process);return new Object();}
private void variableCall() {}
protected int getValue1() { return 1; }protected int getValue2() { return 2; }protected int getValue3() { return 3; }
private void process() {}}}}
non-sealed enum SubSourceEnum extends SourceEnum {}
enum TargetClass {VALUE;
class Inner {class InnerRec {}}}
final class OthersClass {OthersClass(Runnable action) {action.run();}
void thisMethod() {}}