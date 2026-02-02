package test;
interface TestInterface {}
strictfp class SourceClass implements TestInterface {// Local inner class (source_feature)public void createLocalInner() {class SourceLocalInner {}}
{// Anonymous inner class (source_feature)new Runnable() {@Overridepublic void run() {}};}
default int methodToMove(TargetClass target) {int total = 0;TargetClass.Inner.Rec rec = target.new Inner().new Rec();
// Synchronized statementsynchronized (rec) {try {// Accessor methods (3) in exception throwing statementsrec.setValue(target.new Inner().getVal1());total += rec.getValue().length();
rec.setValue(target.new Inner().getVal2());total += rec.getValue().length();
rec.setValue(target.new Inner().getVal3());total += rec.getValue().length();} catch (NullPointerException e) {// No new exception}}
// Variable call + contains target parameter (per_condition)target.toString();rec.toString();
return total;}}
public class TargetClass {public class Inner {public class Rec {private String value;
// Accessor methodspublic String getValue() { return value; }public void setValue(String val) { this.value = val; }}
// Accessor methods (target_feature)public String getVal1() { return "val1"; }public String getVal2() { return "val2"; }public String getVal3() { return "val3"; }
// Local inner class (target_feature)public void createLocalInner() {class TargetLocalInner {}}}}