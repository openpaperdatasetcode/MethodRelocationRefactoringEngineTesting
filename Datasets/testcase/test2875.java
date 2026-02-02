package test;
import java.lang.reflect.Method;
private enum SourceEnum {INSTANCE;
static class StaticNested {class SourceInner {void methodToMove() {} // Overload exists
void methodToMove(TargetEnum.TargetInnerRec target) {// Type declaration statementObject obj;// CastExpression with numbers=2obj = (Number) 2;
// IfStatement with target featuresif (target != null) {super.toString(); // Super keywordstarget.superField = 2;return; // Return statement}
// Variable callint val = target.value;// NullPointerException riskif (val == 0) throw new NullPointerException();
// Used by reflectiontry {Method method = getClass().getMethod("methodToMove");} catch (Exception e) {}
// Local inner classclass LocalInner {}}}}}
private enum TargetEnum {INSTANCE;
/**
Javadoc for TargetInnerRec
*/
record TargetInnerRec(int value) extends SuperClass {
TargetInnerRec {
// Anonymous inner class
Runnable anon = new Runnable() {
public void run() {}
};
}
}
}
class SuperClass {int superField;}