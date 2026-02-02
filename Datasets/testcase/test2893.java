import java.util.ArrayList;import java.util.List;
class SourceClass {class SamePackageOther {private void checkSuperField(TargetSubclass target) {if (target.superField == 3) {System.out.println("Super field value matches");}}}
protected List<String> methodToMove(TargetClass target) {if (!(target instanceof TargetSubclass)) {throw new IllegalArgumentException("Target must be TargetSubclass instance");}TargetSubclass targetSub = (TargetSubclass) target;
new SamePackageOther().checkSuperField(targetSub);assert targetSub.superField == 3 : "Super field value incorrect";
super.toString();
List<String> result = new ArrayList<>();for (int i = 0; i < 3; i++) {switch (i) {case 0:result.add("Value: " + targetSub.superField);break;case 1:result.add("Target nested: " + new TargetClass.StaticNested().getName());break;case 2:result.add("Inner rec: " + targetSub.new InnerRec().doAction());break;}}
return result;}
strictfp List<String> callMethod(TargetClass target) {return (t) -> methodToMove(t).stream().toList();}
// Local inner classclass LocalInner {void invokeMethod(TargetClass target) {methodToMove(target);}}
// Anonymous inner classRunnable anonymous = new Runnable() {@Overridepublic void run() {new LocalInner().invokeMethod(new TargetSubclass());}};}
private class TargetClass {static class StaticNested {String getName() {return "StaticNested";}}
class InnerRec {String doAction() {return "InnerRecAction";}}}
private class TargetSubclass extends TargetClass {int superField = 3;}
