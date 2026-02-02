package test;
import diffpackage.DiffPackageOthers;import java.util.function.Supplier;
public class SourceClass {static class SourceStaticNested {}
class SourceInner {synchronized TargetClass<String> varargsMethod(TargetClass<String>... targets) {DiffPackageOthers diffOthers = new DiffPackageOthers();private String localVar = diffOthers.obj.field;
TargetClass<String> rawTarget = new TargetClass<>();Object genResult1 = rawTarget.<Integer>genericMethod(1);Object genResult2 = rawTarget.<String>genericMethod("param");Object genResult3 = rawTarget.<Boolean>genericMethod(true);
TargetClass.InnerClass inner = SourceClass.this.new TargetClass<>().new InnerClass();inner.innerMethod();
if (targets.length == 0) {return null;}String varCall = targets[0].targetField;return targets[0];}}
void methodWithLocalClass() {class SourceLocalInner {}}}
/**
Javadoc for TargetClass: Generic class with private access,
contains local inner class and generic method
@param <T> Type parameter for generic functionality*/private class TargetClass<T> {String targetField = "targetInstanceVal";
class InnerClass {void innerMethod() {}}
protected Object genericMethod(U param) {
class TargetLocalInner {
U localParam = param;
}
return new TargetLocalInner().localParam;
}
void callInFunctionalInterface() {Supplier<Integer> supplier = () -> OthersClass.staticMethod(this);supplier.get();}}
class OthersClass {protected static int staticMethod(TargetClass<?> target) {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();return inner.varargsMethod(target) != null ? 1 : 0;}}
package diffpackage;
public class DiffPackageOthers {public ObjWithField obj = new ObjWithField();
public static class ObjWithField {public String field = "diffPackageFieldVal";}}