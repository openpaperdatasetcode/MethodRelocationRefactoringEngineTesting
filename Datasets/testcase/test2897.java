package samepkg;
private class SourceClass {public static class StaticNestedSource {}
public class SourceInnerClass {public TargetClass process(TargetClass targetParam) {// Constructor invocationStaticNestedSource nested = new StaticNestedSource();TargetClass.AnonymousHelper helper = targetParam.new AnonymousHelper();
// Anonymous inner class in sourceRunnable sourceAnonymous = new Runnable() {@Overridepublic void run() {targetParam.doSomething();}};sourceAnonymous.run();
// ExpressionStatement (source pos, obj.field x2)private int field1 = targetParam.fieldA;private int field2 = targetParam.fieldB;
// 2 protected CastExpressionprotected Object cast1 = (Object) targetParam.fieldA;protected String cast2 = (String) String.valueOf(targetParam.fieldB);
// Variable calltargetParam.updateFields(field1 + field2);helper.process(cast1, cast2);
// NullPointerException handlingif (targetParam == null) {throw new NullPointerException("Target instance cannot be null");}
// Override violation: implement parent method with incompatible signaturetargetParam.overrideViolationMethod(10);
return targetParam;}}}
package samepkg;
protected class TargetClass extends ParentClass {public int fieldA;public int fieldB;
// Anonymous inner class in targetpublic class AnonymousHelper {public void process(Object o1, Object o2) {}}
public void doSomething() {}
public void updateFields(int val) {fieldA += val;fieldB += val;}
// Override violation: parent method has String parameter, child uses int@Overridepublic void overrideViolationMethod(int arg) {}}
package samepkg;
abstract class ParentClass {public abstract void overrideViolationMethod(String arg);}