package test;
import java.util.List;
// Same package others class for ConstructorInvocationclass SamePackageOthers {// ConstructorInvocation (private modifier, obj.field = 3, pos: same_package_others)private static TargetClass createTarget() {TargetClass target = new TargetClass();target.objField = 3;return target;}}
// Target class (default modifier + anonymous inner class + inner class)class TargetClass {int objField; // target_feature: obj.field
public TargetClass() {// Target feature: anonymous inner classRunnable r = new Runnable() {@Overridepublic void run() {}};}
class TargetInner {} // target_inner
// Chain methods for call_method: obj.m1().m2().m3()public TargetClass m1() { return this; }public TargetClass m2() { return this; }public String m3() { return String.valueOf(objField); }}
// Parent class for source extends featureclass SourceParent {}
// Source class (protected modifier + extends + static nested + anonymous inner class)protected class SourceClass extends SourceParent {// Source feature: static nested classstatic class SourceStaticNested {}
// Source feature: anonymous inner classprivate final Runnable anonymous = new Runnable() {@Overridepublic void run() {new SourceInner().methodToMove(List.of(), new TargetClass().new TargetInner());}};
class SourceInner {// Abstract method (default access + TargetClass return + method types parameter is:List)public abstract TargetClass methodToMove(List<Integer> list, TargetClass.TargetInner inner);
// Concrete implementationclass SourceConcrete extends SourceInner {@Overridepublic TargetClass methodToMove(List<Integer> list, TargetClass.TargetInner inner) {// Variable callTargetClass target = SamePackageOthers.createTarget();SourceStaticNested nested = new SourceStaticNested();
// Type declaration statementclass LocalType {TargetClass process(TargetClass t) {t.objField += list.size();return t;}}LocalType local = new LocalType();
// Access_instance_fieldint fieldVal = target.objField;
// Call_method (pos: do-while, obj.m1().m2().m3())String chainResult;do {chainResult = target.m1().m2().m3();} while (chainResult.length() < 2);
// No new exception thrownreturn local.process(target);}}}}