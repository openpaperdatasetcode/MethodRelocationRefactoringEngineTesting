package test;
import java.io.IOException;
private class SourceClass implements Runnable {static class StaticNested1 {}static class StaticNested2 {}
class Inner {class RecursiveInner {final void process(TargetClass target) {labeled: {try {OthersClass others = new OthersClass();others.handle(target.staticNested.field, 1);} catch (IOException e) {throw new RuntimeException(e);}
if (target.staticNested.value > 0) {break labeled;}}
target.inner.method();}
final void process(String param) {// Overloaded method}}}
@Overridepublic void run() {}}
class TargetClass extends SuperClass {Inner inner = new Inner();static StaticNested staticNested = new StaticNested();
static class StaticNested {int field;int value;}
class Inner {void method() {}}}
class SuperClass {}
class OthersClass {public void handle(int field, int param) throws IOException {}}