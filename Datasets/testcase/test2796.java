package test;
import java.util.List;import java.util.ArrayList;
private class SourceClass {class SourceInner {}
public void createLocalInner() {class LocalInnerSource {}}
// Overload exists (overload_exist feature)private int methodToMove(TargetClass target) {return 0;}
private int methodToMove(TargetClass... targets) {for (TargetClass target : targets) {// If/else conditions with abstract method chain: obj.m1().m2().m3()if (target != null) {OthersClass others = new ConcreteOthersClass();List<String> chainResult = others.m1(target).m2().m3();}
// Synchronized statementsynchronized (target) {// Type declaration statementTargetClass.LocalTargetInner typeDecl;// Variable call + access target's field (per_condition)target.toString();String targetField = target.targetField;
// Switch statement + CaseDefaultExpression (numbers:3, modifier:protected)switch (targetField.length()) {case 3 -> protected String case1 = "case1";case 4 -> protected String case2 = "case2";default -> protected String case3 = "default";}}
// Requires_try_catchtry {if (target.targetField == null) throw new IllegalArgumentException();} catch (IllegalArgumentException e) {e.printStackTrace();}}return targets.length;}}
class TargetClass {public String targetField = "targetVal"; // Source contains target's field (per_condition)
public void createTargetLocalInner() {// Local inner class (target_feature)class LocalTargetInner {}}}
abstract class OthersClass {public abstract ChainClass m1(TargetClass target);
public static class ChainClass {public ChainClass m2() { return this; }public List<String> m3() { return new ArrayList<>(); }}}
class ConcreteOthersClass extends OthersClass {@Overridepublic ChainClass m1(TargetClass target) {return new ChainClass();}}
