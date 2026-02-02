package test;
interface TestInterface {}
class SuperClass {}
class OtherClass {protected TargetClass process(TargetClass.InnerClass inner) {return new TargetClass();}}
class SourceClass extends SuperClass {class MemberInner {}
TargetClass methodToMove(TargetClass... targets) {for (TargetClass target : targets) {TargetClass.InnerClass inner = target.new InnerClass();
switch (inner.state) {case 0:continue;case 1:target.variableCall();break;default:break;}
TargetClass result = new OtherClass().process(inner);new OtherClass().process(this);}
Runnable r = new Runnable() {public void run() {}};
return targets[0];}}
public class TargetClass implements TestInterface {class InnerClass {int state;}
void variableCall() {}}