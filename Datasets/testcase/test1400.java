package sourcepkg;
import java.util.ArrayList;import targetpkg.TargetClass;
private class SourceClass {// Static field for depends_on_static_fieldprivate static final String STATIC_FIELD = "static_dep";
// Anonymous inner class (source feature)Runnable anon = new Runnable() {@Overridepublic void run() {}};
// Static nested class (source feature)static class StaticNested {}
public final <T extends TargetClass.TargetParam> void methodToMove(T param) { // generic type parameter// contains target parameter (per_condition)if (param == null) {throw new NullPointerException(); // NullPointerException}
// constructor invocationStaticNested nested = new StaticNested();TargetClass target = new TargetClass();TargetClass.LocalInnerHolder holder = target.new LocalInnerHolder();
// super constructor invocationParentClass parent = new ParentClass() {};
// variable callnested.toString();holder.useLocalInner();String staticVal = STATIC_FIELD; // depends_on_static_field
// overloading feature (pos:for)for (int i = 0; i < 2; i++) { // 2int result = parent.overloadMethod(2, target.createChain().m1().m2().m3()); // parent_class, overloading, obj.m1().m2().m3()}
// return statementif (staticVal.isEmpty()) {return;}
// Target inner usagetarget.new LocalInnerHolder().localInner.action(param);}}
class ParentClass {// Overloading methods (parent_class)public int overloadMethod(int num, int chainResult) { return num + chainResult; }public int overloadMethod(String str, int chainResult) { return str.length() + chainResult; }}
package targetpkg;
private class TargetClass {static class TargetParam {}
// target_feature: local inner classclass LocalInnerHolder {LocalInner localInner;
LocalInnerHolder() {class LocalInner {void action(TargetParam param) {}}this.localInner = new LocalInner();}
void useLocalInner() {localInner.action(new TargetParam());}}
// For obj.m1().m2().m3()ChainClass createChain() {return new ChainClass();}
static class ChainClass {ChainClass m1() { return this; }ChainClass m2() { return this; }int m3() { return 2; } // base type return}}
