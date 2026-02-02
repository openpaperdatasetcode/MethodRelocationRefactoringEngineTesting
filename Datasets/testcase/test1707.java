package test;
import java.io.IOException;import java.util.ArrayList;import java.util.List;
sealed class SourceClass permits SubSourceClass {class MemberInner1 {class MemberInner2 {private TargetClass.MemberInner instanceMethod(TargetClass target) throws IOException {// Access target fieldint fieldVal = target.targetField;
for (int i = 0; i < 5; i++) {if (i == 3) {continue;}variableCall();}
if (target == null) {throw new IllegalArgumentException("Target is null");}
// Raw type usageList rawList = new ArrayList();
return target.new MemberInner();}
private void variableCall() {}}}}
final class SubSourceClass extends SourceClass {}
final class TargetClass extends ParentClass implements Runnable {int targetField;
class MemberInner {}
@Overridepublic void run() {}}
class ParentClass {}