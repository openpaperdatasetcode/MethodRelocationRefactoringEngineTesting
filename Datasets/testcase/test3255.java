package test;
class ParentClass {public void methodToOverride(TargetClass target) {}}
protected class SourceClass extends ParentClass {static class StaticNested {}
@Overridevoid methodToOverride(TargetClass targetParam) {new Runnable() {@Overridepublic void run() {}};
try {Runnable r = () -> System.out.println(this.toString());r.run();
targetParam.doSomething();privateWhile(targetParam);} catch (Exception e) {}}
private void privateWhile(TargetClass target) {while (TargetClass.staticField > 0) {target.doSomething();TargetClass.staticField--;}}}
public class TargetClass extends ParentClass {static int staticField = 5;int value = 10;
static class TargetStaticNested {}
void doSomething() {}}