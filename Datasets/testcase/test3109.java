package test;
protected class ParentClass {int parentField;}
protected class TargetClass<T> extends ParentClass {static class TargetStaticNested {}class TargetInner {record TargetInnerRec(T value) {}}}
// Different packagepackage other;
import test.TargetClass;import test.ParentClass;
public class DiffPackageClass {private void process(TargetClass.TargetInner.TargetInnerRec rec) {loop:for (int i = 0; i < 5; i++) {if (i == 2) {// BreakStatement with super.field and 2ParentClass parent = new TargetClass<>();parent.parentField = 2;break loop;}}}}
// Same packagepackage test;
protected class SourceClass {static class SourceStaticNested {}
public SourceClass() {Runnable r = new Runnable() {@Overridepublic void run() {}};}
class SourceInner {record SourceInnerRec() {final int methodToMove(TargetClass<Integer>.TargetInner.TargetInnerRec param) {// Variable callint var = param.value();TargetClass.TargetStaticNested nested = new TargetClass.TargetStaticNested();
// Switch caseswitch (var) {case 1:var *= 2;break;case 2:var += 3;break;default:var = 0;}
return var;}}}}