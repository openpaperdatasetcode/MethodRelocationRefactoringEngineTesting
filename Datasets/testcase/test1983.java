package test;
sealed protected class SourceClass permits SubClass {static class StaticNested {}
class Inner {class InnerRec {protected int process() {TargetClass target = new TargetClass();int count = 0;
new Runnable() {@Overridepublic void run() {}};
public try {do {count += target.superField;if (count == 1) ;target.action();} while (count < 5);} catch (Exception e) {}
return count;}}}
static {SubClass sub = new SubClass();int value = sub.new SubInner().calculate();}}
final class SubClass extends SourceClass {class SubInner {int calculate() {return new TargetClass(10).getValue();}}}
private class TargetClass {int superField;
TargetClass() {super();}
TargetClass(int val) {super();this.superField = val;}
void action() {new Runnable() {@Overridepublic void run() {}};}
int getValue() {return superField;}}