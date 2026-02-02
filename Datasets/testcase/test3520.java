package test;
import diffpackage.OthersClass;
abstract class SourceClass {protected int outerProtectedField = 1;private TargetClass target = new TargetClass();
void moveMethod(TargetClass param) {int count = 0;while (count < 5) {OthersClass.process(param);
switch (param.this.field) {case 1:variableCall(param);break;default:continue;}count++;}System.out.println(outerProtectedField);}
private void variableCall(TargetClass target) {target.staticNested.doAction();}}
class TargetClass {int field = 1;
static class StaticNested {void doAction() {}}
StaticNested staticNested = new StaticNested();}
package diffpackage;
import test.TargetClass;
public class OthersClass {public static void process(TargetClass target) {switch (target.field) {case 1:target.field = 1;break;}}}