package test;
import java.util.Arrays;import other.DiffPackageClass;
final class SourceClass {class SourceInner {class SourceInnerRec {public Object methodToMove(TargetClass target) {// EnhancedForStatement with target features (diff_package_others)TargetClass[] targets = {target};DiffPackageClass.process(() -> {for (TargetClass t : targets) {privateMethod(t.field + 1);}});
// Labeled statementprocessLabel: {if (target.field < 0) {break processLabel;}TargetClass.TargetInner inner = target.new TargetInner();inner.setValue(target.field);}
// Variable callint var = target.field;TargetClass.TargetInner innerVar = target.new TargetInner();innerVar.getValue();
return var;}
private void privateMethod(int val) {}}}
void localInnerMethod() {class LocalInner {} // Local inner class}
{new Runnable() {}; // Anonymous inner class}}
strictfp class TargetClass extends ParentClass {int field;
class TargetInner {private int value;
void setValue(int val) {this.value = val;}
int getValue() {return value;}}
int callMethod() {// Object initializationTargetClass instance = new TargetClass() {{super();field = 5;}};return instance.field;}}
class ParentClass {}
// Class in different packagepackage other;
import java.util.function.Runnable;
public class DiffPackageClass {public static void process(Runnable runnable) {runnable.run();}}