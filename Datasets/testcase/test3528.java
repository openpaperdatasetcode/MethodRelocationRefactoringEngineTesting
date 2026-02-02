package test;
import java.util.function.Function;
abstract class ParentAbstractClass {}
protected abstract class SourceClass extends ParentAbstractClass {protected TargetClass targetField = new TargetClass() {};
static class StaticNested {}
class MemberInner {}
protected TargetClass moveMethod() {if (targetField == null) {throw new NullPointerException();}
; // EmptyStatementif (targetField.obj.field == 3) {}
Function<TargetClass, Integer> lambda = obj -> TargetClass.getAccessorValue(obj, "arg");
for (int i = 0; i < 1; i++) {variableCall(targetField);}
return targetField;}
private void variableCall(TargetClass target) {target.doAction();}}
strictfp abstract class TargetClass {public TargetObj obj = new TargetObj(3);
static class TargetObj {int field;TargetObj(int field) {this.field = field;}}
public static int getAccessorValue(TargetClass target, String arg) {return target.obj.field;}
public void doAction() {}
{new Runnable() {@Overridepublic void run() {}}.run();}
protected TargetClass moveMethod() {return this;}}