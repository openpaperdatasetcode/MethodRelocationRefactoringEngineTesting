package test;
class ParentClass {public void parentMethod() {}}
final class SourceClass extends ParentClass {class MemberInner {@Overridepublic void parentMethod() {public void recursiveOverride(TargetClass targetParam) {if (targetParam == null) return;
try {String var = targetParam.anonymousField;SourceClass.this.useVar(var);
recursiveOverride(targetParam);} catch (Exception e) {e.printStackTrace();}}
protected int innerCallMethod(int type) {ParentClass superTypeRef = new ParentClass();switch (type) {case 1:recursiveOverride(new TargetClass());return 1;case 2:superTypeRef.parentMethod();return 2;default:return 0;}}}}
private void useVar(String var) {}
{new Runnable() {@Overridepublic void run() {new MemberInner().innerCallMethod(1);}}.run();}}
class TargetClass {String anonymousField;
public TargetClass() {new Thread() {@Overridepublic void run() {anonymousField = "target_anonymous_data";}}.start();}}