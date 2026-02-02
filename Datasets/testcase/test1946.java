package test;
import other.OthersClass;import java.util.function.Function;
public sealed class SourceClass implements SomeInterface {static class StaticNested {}
final void method(TargetClass target) {// Local inner classclass LocalProcessor {void processInnerRec(TargetClass.Inner.InnerRec rec) {rec.setValue(1);}}
TargetClass.Inner inner = target.new Inner();TargetClass.Inner.InnerRec innerRec = inner.new InnerRec();
// Synchronized statementsynchronized (innerRec) {innerRec.increment();}
// Variable call to target's inner recursive class fieldtarget.field = innerRec.getValue();
new LocalProcessor().processInnerRec(innerRec);}
final void method(TargetClass target, int value) {// VariableDeclarationStatement with obj.field=1 in different package logicTargetClass.Inner.InnerRec rec = target.new Inner().new InnerRec();private int processedValue = rec.otherField = 1;
// While loop with lambda call to others class accessor methodint i = 0;while (i < value) {Function<TargetClass, TargetClass> func = (t) -> OthersClass.getAccessor(t).getValue();TargetClass result = func.apply(target);i++;}}}
interface SomeInterface {}
private class TargetClass {int field;
class Inner {class InnerRec {private int value;int otherField;
void setValue(int val) {value = val;}
int getValue() {return value;}
void increment() {value++;}}
// Anonymous inner class in targetRunnable initializer = new Runnable() {public void run() {new InnerRec().setValue(0);}};}}
package other;
import test.TargetClass;
public class OthersClass {public static TargetAccessor getAccessor(TargetClass target) {return new TargetAccessor(target);}
public static class TargetAccessor {private TargetClass target;
public TargetAccessor(TargetClass target) {this.target = target;}
// Accessor methodpublic TargetClass getValue() {return target;}}}