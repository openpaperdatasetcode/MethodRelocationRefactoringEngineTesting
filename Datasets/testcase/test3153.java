package test;
interface SourceInterface {}interface TargetInterface {}
private class TargetClass implements TargetInterface { // target_feature: implementsString targetField;
public TargetClass() {// target_feature: anonymous inner classRunnable r = new Runnable() {@Overridepublic void run() {}};}
class TargetInner {} // target_inner}
class SourceClass implements SourceInterface { // source_feature: implements// source_feature: first anonymous inner classprivate Runnable r1 = new Runnable() {@Overridepublic void run() {}};
// source_feature: second anonymous inner classprivate Runnable r2 = new Runnable() {@Overridepublic void run() {}};
class SourceInner {private TargetClass methodToMove(TargetClass.TargetInner inner) {// Constructor invocationTargetClass target = new TargetClass();SourceInner sourceInner = new SourceInner();
// Variable callString var = target.targetField;
// If statementif (var == null) {var = "default";}
// Switch caseswitch (var.length()) {case 0:var = "empty";break;case 7:var = "valid";break;default:var = "other";}
// Try statementtry {target.targetField = var.toUpperCase();} catch (NullPointerException e) {target.targetField = "error";}
return target;}}}