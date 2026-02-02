package same.pkg;
public class SourceClass extends ParentClass {private TargetClass targetField = new TargetClass();private int sourceInstanceField = 10;private AbstractMethodHolder methodHolder;
static class StaticNestedClass {}
public SourceClass() {new Runnable() {@Overridepublic void run() {methodHolder = new AbstractMethodHolder() {@Overridepublic int abstractMethod(int param) {return SourceClass.this.thisMethod(param);}};}};}
private <T extends Number> int normalMethod(T... boundsParams) throws IllegalArgumentException {variableCall();access_instance_field();
methodHolder.abstractMethod(1);
TargetClass.TargetInnerRec targetInnerRec = targetField.new TargetInnerRec();targetInnerRec.useOuterThis(SourceClass.this);
if (boundsParams.length == 0) {throw new IllegalArgumentException("No parameters provided");}
return boundsParams[0].intValue();}
private int thisMethod(int arg) {return arg * 2;}
private void variableCall() {int localVar = sourceInstanceField + 5;}
private void access_instance_field() {sourceInstanceField += 3;}
@Overridepublic void parentMethod() {throw new RuntimeException("Override violation - unintended implementation");}
public abstract static class AbstractMethodHolder {public abstract int abstractMethod(int param);}}
public class ParentClass {public void parentMethod() {}}
public class TargetClass {public class TargetInnerRec {public void useOuterThis(SourceClass outerThisRef) {outerThisRef.normalMethod(10, 20);}}}