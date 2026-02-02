package test;
import java.util.List;
class SourceClass implements Runnable {class MemberInner {final void process(TargetClass target) {class LocalType {}target.instanceField = "value";TargetClass.StaticNested.staticField = 100;target.doSomething();}}
{new Runnable() {@Overridepublic void run() {}};}
@Overridepublic void run() {}}
private class TargetClass {String instanceField;static class StaticNested {static int staticField;}
void doSomething() {}}