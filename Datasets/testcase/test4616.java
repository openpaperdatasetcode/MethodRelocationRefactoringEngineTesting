package test;
class SourceClass {static class StaticNested {class InnerRec {synchronized void testMethod(TargetClass.Inner innerParam) {innerParam.doSomething();InnerHelper helper = new InnerHelper();helper.useInner(innerParam);Runnable r = new Runnable() {public void run() {innerParam.doSomething();}};}
private class InnerHelper {void useInner(TargetClass.Inner inner) {inner.doSomething();}}}}}
final class TargetClass {class Inner {void doSomething() {}}}