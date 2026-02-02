package test;
import java.util.List;import java.util.ArrayList;
strictfp public class SourceClass {// Member inner class (source_feature)class SourceInner {}
public void createLocalInner() {class LocalInnerSource {}}
public Object methodToMove(List<TargetClass> targetList) {super();
// Constructor invocationTargetClass newTarget = new TargetClass();// Type declaration statement (raw_type)TargetClass rawTarget;
List<Object> results = new ArrayList<>();for (TargetClass target : targetList) {// Variable call + contains target parameter (per_condition)target.toString();rawTarget = target;
// Access instance methodtarget.instanceMethod();
// Depends on inner classTargetClass.TargetLocalInner inner = target.createLocalInner();results.add(inner);}
return results;}}
class TargetClass {public void instanceMethod() {}
public TargetLocalInner createLocalInner() {// Local inner class (target_feature)class TargetLocalInner {}return new TargetLocalInner();}}
class OthersClass {protected void callMethod(List<TargetClass> targets) {try {// new ClassName().method() in exception throwing statementsnew SourceClass().methodToMove(targets);} catch (Exception e) {throw new RuntimeException("Call failed", e);}}}