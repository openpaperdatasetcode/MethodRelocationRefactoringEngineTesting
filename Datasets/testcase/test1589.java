package test;
final class SourceClass {protected int outerProtected = 10;
public class MemberInner {public class InnerRec {private int process(TargetClass target) {// Local inner classclass TargetHandler {int handle(TargetClass t) {// Access outer protected fieldreturn t.value + outerProtected;}}TargetHandler handler = new TargetHandler();
// Anonymous inner classRunnable logger = new Runnable() {@Overridepublic void run() {// Variable call - access target's fieldSystem.out.println("Target value: " + target.value);}};
// Super keywordSystem.out.println(super.toString());
// Public ForStatement with target's static field (1 occurrence)public for (int i = 0; i < TargetClass.STATIC_FIELD; i++) {target.value += i;}
// Call inner class's final constructor and method in for loopint sum = 0;for (int i = 0; i < 2; i++) {sum += target.new Inner().calculate(i);}
logger.run();return handler.handle(target);}}}}
private class TargetClass {public static final int STATIC_FIELD = 3;public int value;
public class Inner {private int factor;
// Final constructorpublic final Inner() {this.factor = 2;}
public int calculate(int num) {// Local inner class in targetclass Calculator {int compute(int n) {return n * factor;}}return new Calculator().compute(num);}}}