package test;
import otherpackage.OthersClass;
abstract class SourceClass {class MemberInner1 {}class MemberInner2 {}
static int staticField;
Object varargsMethod(TargetClass... targets) {// Access target fieldfor (TargetClass target : targets) {String fieldVal = target.targetField;}
// DoStatement in diff_package_others with obj.field accessOthersClass others = new OthersClass();int count = 0;do {if (count >= targets.length) break;protected String val = targets[count].targetField;count++;} while (count < 1);
variableCall();
// Depends on static fieldint val = SourceClass.staticField;
return new Object();}
private void variableCall() {}}
private class TargetClass {String targetField;
{new Runnable() {};}}
package otherpackage;
public class OthersClass {}