package test;
sealed enum SourceEnum permits SourceEnum.StaticNested {INSTANCE;
protected int outerProtectedField = 3;
static class StaticNested extends SourceEnum {record InnerRec(int value) {private void methodToMove(TargetEnum.TargetInner... targets) {// Type declaration statementboolean flag;// Empty statement;// BooleanLiteral with numbers=3 (simulated)flag = (3 > 0);
// Variable callint val = outerProtectedField; // Access outer protected
for (int i = 0; i < 3; i++) {if (i == 1) {continue; // Continue statement;}}
// Anonymous inner classRunnable anon = new Runnable() {public void run() {}};}}}
static {// Instance method in static code blocksObject obj = INSTANCE.new OuterInner().protectedMethod();}
class OuterInner {protected Object protectedMethod() {return new Object();}}}
non-sealed enum TargetEnum {INSTANCE;
class TargetInner {}}