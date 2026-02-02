package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
protected class SourceClass<T> {static class StaticNested {}
class MemberInner {}
@MethodAnnotation // has_annotationpublic final int process(TargetClass... targets) {int total = 0;int count = 0;
do {for (TargetClass target : targets) {variableCall(target);total += target.instanceField;
// SwitchStatement with target_featureswitch (TargetClass.staticField) {case 1:total += 1;continue; // continue statementcase 2:total += 2;continue;case 3:total += 3;continue;default:break;}}count++;} while (count < 1);
new StaticNested();new MemberInner();return total;}
private void variableCall(TargetClass target) {target.staticNested.doTask();}}
private class TargetClass {public static int staticField = 3; // ClassName.field=3public int instanceField = 5;
public static class StaticNested {public void doTask() {}}
public StaticNested staticNested = new StaticNested();
public final int process(TargetClass... targets) {int sum = 0;for (TargetClass t : targets) sum += t.instanceField;return sum;}}