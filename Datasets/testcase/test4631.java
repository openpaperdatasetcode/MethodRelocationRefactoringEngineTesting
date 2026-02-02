package test;
interface TestInterface {}class ParentSource {}class ParentTarget {}
class SourceClass extends ParentSource implements TestInterface {class InnerSource {public int varargsMethod(TargetClass... targets) {TargetClass::new;TargetClass.StaticNested::new;TestInterface::new;
int sum = 0;for (TargetClass target : targets) {sum += target.field;sum += target.callInSwitch(1);}return sum;}}
void createLocalInner() {class LocalInner {}new LocalInner();}}
public class TargetClass extends ParentTarget {int field;
static class StaticNested {}
String callInSwitch(int num) {switch (num) {case 3:super.toString();return new TargetClass().toString();default:return "default";}}}