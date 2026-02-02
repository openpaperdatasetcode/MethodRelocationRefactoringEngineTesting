package source;
import target.TargetClass;import java.util.function.Supplier;
strictfp class SourceClass {TargetClass methodToMove(int baseParam, TargetClass.InnerRec innerRec) {TargetClass target = new TargetClass();
synchronized (target) {target.new AnonymousInner() {};}
Supplier<Object> supplier = () -> this.m1().m2().m3();supplier.get();
return innerRec.target();}
SourceClass m1() { return this; }SourceClass m2() { return this; }Object m3() { return new Object(); }}
package target;
public class TargetClass {record InnerRec(TargetClass target) {}
class AnonymousInner {}
// Override violation example (assuming parent has different signature)@Overridepublic String toString(int param) {return "";}}