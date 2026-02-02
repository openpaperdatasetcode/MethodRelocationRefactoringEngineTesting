package test;
final class SourceClass {public static class StaticNested {static void processDoStatement(TargetClass target) {int count = 0;do {if (target.field == 1) {System.out.println("Target field matches: " + target.field);}count++;} while (count < 2);}}
protected TargetClass process(TargetClass target) {assert target != null : "Target cannot be null";super.toString();
TargetClass newTarget = new TargetClass();newTarget.field = target.field;
switch (target.field) {case 1:StaticNested.processDoStatement(target);newTarget = target.callInnerMethod();break;default:newTarget.field = 0;}
return newTarget;}
protected TargetClass process(TargetClass target, int extraParam) {TargetClass newTarget = process(target);newTarget.field += extraParam;return newTarget;}
private Runnable anonTask = new Runnable() {@Overridepublic void run() {process(new TargetClass());}};}
public class TargetClass {int field;
public TargetClass() {super();}
public class MemberInner {TargetClass updateField(int value) {TargetClass.this.field = value;return TargetClass.this;}}
public TargetClass callInnerMethod() {return new MemberInner().updateField(field);}}
