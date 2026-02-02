package test;
import other.DiffPackageOthers;import java.util.function.Supplier;
interface SourceImplInterface {}
public class TargetClass {String field1;int field2;boolean field3;
public static class TargetStaticNested {public void nestedInstanceMethod(TargetClass target) {target.field1 += "_nestedUpdated";}}
public TargetClass(String f1, int f2, boolean f3) {this.field1 = f1;this.field2 = f2;this.field3 = f3;}
public void targetInstanceMethod() {field2 += 10;}}
final class SourceClass implements SourceImplInterface {public final void recursiveMethod(TargetClass target, int depth) {if (depth <= 0) {return;}
typeDeclaration: {class SourceLocalType {}SourceLocalType localObj = new SourceLocalType();}
TargetClass.TargetStaticNested staticNested = new TargetClass.TargetStaticNested();DiffPackageOthers diffObj = new DiffPackageOthers();
Supplier<Object> lambda = () -> new SourceClass() {public SourceClass() {super();}};lambda.get();
enhancedFor: {TargetClass[] targetArr = {new TargetClass("val1", 1, true),new TargetClass("val2", 2, false),new TargetClass("val3", 3, true)};diffObj.processTargets(targetArr);}
int count = 0;while (count < 3) {variableCall(target);accessInstanceMethod(target);staticNested.nestedInstanceMethod(target);count++;}
recursiveMethod(new TargetClass(target.field1, target.field2, target.field3), depth - 1);}
private void variableCall(TargetClass target) {target.field1 += "_varUpdated";}
private void accessInstanceMethod(TargetClass target) {target.targetInstanceMethod();}}
package other;
import test.TargetClass;
public class DiffPackageOthers {public void processTargets(TargetClass[] targets) {for (TargetClass t : targets) {t.field1 += "_diffPackage";t.field2 += 5;t.field3 = !t.field3;}}}