package test;
protected class SourceClass {private String outerPrivateField = "private";
protected TargetClass varargsMethod(TargetClass... targets) {// Access target fieldfor (TargetClass target : targets) {String fieldVal = target.targetField;}
variableCall();
// Access outer private memberString privateVal = this.outerPrivateField;
return targets.length > 0 ? targets[0] : new TargetClass();}
private void variableCall() {}}
public class TargetClass implements Runnable {String targetField;
class MemberInner {}
@Overridepublic void run() {}}