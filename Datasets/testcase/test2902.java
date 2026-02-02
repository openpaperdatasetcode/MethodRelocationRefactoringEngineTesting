public class SourceClass {class MemberInner {class InnerRec {private void methodToMove(TargetClass... targets) {if (targets == null || targets.length == 0) {throw new NullPointerException("Targets array cannot be null or empty");}
class LocalInner {void processTarget(TargetClass target) {System.out.println(target.targetField);TargetClass.MemberInner targetInner = target.new MemberInner();targetInner.doAction();}}
LocalInner local = new LocalInner();for (TargetClass target : targets) {local.processTarget(target);}}}}}
class TargetClass {String targetField;
class MemberInner {void doAction() {}}}