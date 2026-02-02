// Source package: same.pkgpackage same.pkg;
import diff.pkg.Target; // Assume Target is in different packageimport java.util.List;
private class Source<T> {static class StaticNested {}
// Anonymous inner classRunnable anon = new Runnable() {@Overridepublic void run() {new SourceInnerRec().instanceMethod(new Target());}};
record SourceInnerRec() {private void instanceMethod(Target targetParam) {// ConstructorInvocation in diff_package_targetpublic Target.Inner inner = new Target.Inner();if (inner.field == 3) {}
// Switch caseint num = targetParam.getNum();switch (num) {case 1:System.out.println("Case 1");break;case 2:System.out.println("Case 2");break;default:// Call method in switchTarget result = Source.this.synchronizedMethod(targetParam);}
// While statementwhile (inner.counter < 5) {inner.counter++;}
// Variable callTarget.Inner varInner = targetParam.createInner();varInner.process();}}
// Call method (source type)private synchronized Target synchronizedMethod(Target target) {
// outerInstance.new InnerClass().methodName()
return new Source<String>().new SourceInner().callTargetMethod(target);
}
class SourceInner {Target callTargetMethod(Target target) {return target;}}}
// Target package: diff.pkgpackage diff.pkg;
public class Target {int num;
class Inner {int field;int counter;
void process() {}}
Inner createInner() {// Local inner class in targetclass LocalInner {}return new Inner();}
int getNum() {return num;}}