package test;
public class SourceClass {public static class StaticNested {public String staticValue = "static_nested";}
public Object process(TargetClass... targets) {super();TargetClass target = targets.length > 0 ? targets[0] : new TargetClass();TargetClass.MemberInner targetInner = target.new MemberInner();
labeled: {int i = 0;while (i < 2) {Object result = targetInner.callProtected(target, staticValue);if (result != null) break labeled;i++;}}
class LocalInner {private void breakInInner() {if (target.thisField == 1) {break labeled;}}}new LocalInner().breakInInner();
Runnable anon = new Runnable() {@Overridepublic void run() {targetInner.update(target.thisField);}};anon.run();
String callResult;do {callResult = targetInner.callPrivate(target);} while (callResult.isEmpty());
return targetInner;}}
protected class TargetClass implements SomeInterface {public int thisField = 1;protected String staticValue = "target_field";
public class MemberInner {public MemberInner() {super();}
protected Object callProtected(TargetClass target, String arg) {return super.toString() + "" + target.thisField + "" + arg;}
private String callPrivate(TargetClass target) {return super.getClass().getSimpleName() + "_" + target.thisField;}
public void update(int value) {thisField = value * 2;}}
@Overridepublic void interfaceMethod() {}}
interface SomeInterface {void interfaceMethod();}