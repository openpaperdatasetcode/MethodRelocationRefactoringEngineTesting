package test;
interface TestInterface {}
public class SourceClass implements TestInterface {protected String outerProtected;class MemberInner {}
void createLocalInner() {class LocalInner {}}
final abstract TargetClass methodToMove(TargetClass target);
// Concrete method demonstrating features (since abstract method can't have body)void demoFeatures(TargetClass target) {TargetClass newTarget = new TargetClass() {@Overridevoid abstractMethod() {}};class LocalType {}LocalType local = new LocalType();
int i = 0;while (i < 5) {target.variableCall();i++;}
System.out.println(super.toString());String access = this.outerProtected;}}
abstract class TargetClass {void variableCall() {}abstract void abstractMethod();}