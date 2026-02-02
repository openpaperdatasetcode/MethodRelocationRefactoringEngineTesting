  }
package test;
interface BaseInterface {int process();}
strictfp class SourceClass implements BaseInterface {private int outerPrivate = 5;private static final String STATIC_FIELD = "source_static";
static class SourceStaticNested {}
@Overridepublic int process() {new Runnable() {@Overridepublic void run() {TargetClass target = new TargetClass();recursiveMethod(target, 3);}}.run();return 0;}
private int recursiveMethod(TargetClass target, int depth) {if (depth <= 0) {return 0;}final int val = (target.innerField + outerPrivate);TargetClass.StaticNested nested = new TargetClass.StaticNested(this) {@OverrideObject innerMethod() {return this.calculate(val);}};variableCall(target, nested);return val + recursiveMethod(target, depth - 1);}
private void variableCall(TargetClass target, TargetClass.StaticNested nested) {int sum = target.innerField + (Integer) nested.innerMethod();System.out.println(STATIC_FIELD + ": " + sum);}}
public class TargetClass {int innerField = 3;
static class StaticNested {private SourceClass outerSource;
public StaticNested(SourceClass outerSource) {this.outerSource = outerSource;}
Object innerMethod() {return 0;}
Object calculate(int param) {return param * 2;}}}