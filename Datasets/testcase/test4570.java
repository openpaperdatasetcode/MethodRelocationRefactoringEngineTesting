package test;
strictfp class SourceClass {public abstract static class AbstractHelper {public abstract void abstractMethod(TargetClass target);}
private int methodToMove(TargetClass targetParam) {AbstractHelper helper = new AbstractHelper() {@Overridepublic void abstractMethod(TargetClass target) {new SourceClass().new InnerClass().useTarget(target);}};helper.abstractMethod(targetParam);
int result = 0;for (int i = 0; i < TargetClass.staticField; i++) {result += targetParam.instanceMethod();}return result;}
public class InnerClass {public void useTarget(TargetClass target) {target.instanceMethod();}}}
class TargetClass {static int staticField = 5;
int instanceMethod() {return 1;}
void createAnonymous() {Runnable r = new Runnable() {public void run() {}};}}
