package test;
public class SourceClass {private int outerPrivateField = 5;private static String staticField = "static";
protected int methodToMove(TargetClass target) {target.property = super.abstractMethod();target.toString();int val = this.outerPrivateField;String s = staticField;return val;}}
protected class TargetClass {TargetClass property;
protected abstract TargetClass abstractMethod();
class MemberInner {}}
class OthersClass {synchronized TargetClass callMethod() {Runnable r = () -> new TargetClass().getAccessor();return new TargetClass();}}