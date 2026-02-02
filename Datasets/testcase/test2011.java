package test;
interface TestInterface {}
public enum SourceEnum implements TestInterface {VALUE1, VALUE2;
private TargetEnum targetField;
SourceEnum() {this.targetField = TargetEnum.TARGET1;}
class SourceInner {private TargetEnum methodToMove() {try {int i = 0;while (i < 3) {if (i == 1) {break;}targetField.callMethod();i++;}return targetField;} catch (Exception e) {return null;}}}
void createAnonymous1() {Runnable r1 = new Runnable() {public void run() {}};}
void createAnonymous2() {Runnable r2 = new Runnable() {public void run() {}};}}
protected enum TargetEnum {TARGET1, TARGET2;
void callMethod() {class LocalInner {void doSomething() {}}new LocalInner().doSomething();}}