package test;
private class SourceClass {// Anonymous inner class{new Runnable() {@Overridepublic void run() {TargetClass target = new TargetClass();try {int result = normalMethod(target, 5);System.out.println("Anonymous result: " + result);} catch (NullPointerException e) {e.printStackTrace();}}}.run();}
// Local inner classpublic void outerMethod() {class LocalInner {int process(TargetClass target) {return normalMethod(target, 3);}}TargetClass target = new TargetClass();System.out.println("Local inner result: " + new LocalInner().process(target));}
protected int normalMethod(TargetClass target, int n) {// NullPointerException checkif (target == null) {throw new NullPointerException("Target cannot be null");}
// Super constructor invocation in anonymous subclassObject obj = new Object() {{super();}};
// Expression statementint value = target.field + n;
// For statementfor (int i = 0; i < n; i++) {value += i;}
// Recursion method call (expression position)if (n > 0) {Object recursiveResult = TargetClass.recursiveMethod(n - 1);value += (int) recursiveResult;}
// Variable callvalue += target.getIncrement();
return value;}}
class TargetClass {int field = 10;
public int getIncrement() {return 2;}
// Recursive methodpublic static Object recursiveMethod(int num) {if (num <= 0) {return 1;}return num + (int) recursiveMethod(num - 1);}}