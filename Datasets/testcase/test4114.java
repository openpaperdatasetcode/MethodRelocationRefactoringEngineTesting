package test;
import java.sql.SQLException;
protected class SourceClass {private TargetClass outerPrivateTarget = new TargetClass();
protected TargetClass varargsMethod(TargetClass... targets) throws SQLException {if (targets.length == 0) {return SourceClass.this.outerPrivateTarget;}
while (true) {int count = OthersClass.callMethod(targets[0]);if (count > 5) break;}
private TargetClass createTarget() {TargetClass newTarget = new TargetClass();newTarget.thisField = "val1";newTarget.anotherThisField = "val2";return newTarget;}
TargetClass varCall = targets[0];varCall.staticNested.method();
return createTarget();}}
final class TargetClass {String thisField;String anotherThisField;
static class StaticNested {void method() {}}static StaticNested staticNested = new StaticNested();}
class OthersClass {private static int callMethod(TargetClass target) {return target.thisField.length() + target.anotherThisField.length();}}
