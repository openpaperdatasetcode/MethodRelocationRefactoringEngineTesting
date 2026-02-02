package test;
abstract class SourceAbstractClass {static class SourceStaticNested {}
class SourceInner {public void methodToMove(TargetAbstractClass targetParam, String... args) {if (args.length == 0) {methodToMove(targetParam, "default");return;}
volatile int num = 1;while (num < args.length) {try {OthersClass others = new OthersClass();int baseVal = others.callSuperMethod();SourceAbstractClass.this.accessOuterInstanceMethod();
class LocalType {}LocalType local = new LocalType();variableCall();
if (baseVal < 0) {throw new IllegalArgumentException("Invalid value");}} catch (IllegalArgumentException e) {num++;}}}
private void variableCall() {}}
void accessOuterInstanceMethod() {}}
/**
Javadoc for TargetAbstractClass
*/
protected abstract class TargetAbstractClass {
{
new Runnable() {
@Override
public void run() {}
};
}
}
class OthersClass extends ParentClass {private int callSuperMethod() {return super.parentInstanceMethod();}}
class ParentClass {int parentInstanceMethod() {return 1;}}