package same;
protected class SourceClass implements Runnable {static class StaticNestedA {}static class StaticNestedB {}
@Overridepublic final void run() {}
public final void process(TargetClass target) {; // Empty statement
TargetClass.Inner inner = target.new Inner(new Helper().initialize(super.toString()));inner.display(target.name);}}
class Helper {public void initialize(String arg) {super.toString();}}
package same;
/**
Target class with member inner class*/private class TargetClass {String name = "target";
/**
Member inner class for handling display*/class Inner {Inner(void initResult) {}
void display(String text) {System.out.println(text);}}}