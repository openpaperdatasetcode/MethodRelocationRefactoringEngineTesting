package same;
interface SourceInterface {}interface TargetInterface {}
protected class Source implements SourceInterface {Target targetField = new Target();
Runnable anonInner1 = new Runnable() {public void run() {}};Runnable anonInner2 = new Runnable() {public void run() {}};
strictfp Target.TargetInner varargsMethod(int... baseParams) {private abstract class AbstractHelper {abstract Target.TargetInner abstractMethod(Target.TargetInner targetInner);}
AbstractHelper helper = new AbstractHelper() {@OverrideTarget.TargetInner abstractMethod(Target.TargetInner targetInner) {return targetInner.instanceMethod(targetField);}};
Target.TargetInner[] innerArray = { helper.abstractMethod(targetField.new TargetInner()) };
super();
switch (baseParams.length) {case 1:break;default:break;}
try {targetField;} catch (Exception e) {}
Target.TargetInner var = targetField.new TargetInner();var.instanceMethod(targetField);
return var;}}
public class Target implements TargetInterface {class TargetInner {public Target.TargetInner instanceMethod(Target target) {class LocalInner {}new LocalInner();return this;}}}