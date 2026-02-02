package test;
private class SourceClass {private int outerField = 5;
public static class StaticNested {public static int staticValue = 10;}
public class SourceInner {public final int process(TargetClass targetParam) {super();TargetClass.MemberInner targetInner = targetParam.new MemberInner();int result = 0;
class LocalType {int compute(int val) {return val * SourceClass.this.outerField;}}LocalType calculator = new LocalType();
do {result += calculator.compute(targetParam.targetField);result += StaticNested.staticValue;} while (result < 50);
Runnable anon = new Runnable() {@Overridepublic void run() {targetInner.update(targetParam.targetField);}};anon.run();
try {result += recursiveMethod(targetParam, 3);} catch (Exception e) {result = 0;}
return result;}
protected Object recursiveMethod(TargetClass target, int depth) {if (depth <= 0) {return target.targetField;}return this.recursiveMethod(target, depth - 1);}}}
private class TargetClass implements SomeInterface {public int targetField;
public class MemberInner {public void update(int value) {targetField = value * 2;}}
@Overridepublic void interfaceMethod() {targetField = 0;}}
interface SomeInterface {void interfaceMethod();}
