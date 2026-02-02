package test;
import java.lang.reflect.Method;
interface MyInterface {void moveMethod(TargetEnum param);}
sealed enum SourceEnum implements MyInterface {INSTANCE;
static class StaticNested {class InnerRec {@Overridepublic void moveMethod(TargetEnum param) {if (param == TargetEnum.VALUE1) {variableCall(param);}
try {Method method = TargetEnum.class.getMethod("moveMethod", TargetEnum.class);method.invoke(param, param);} catch (Exception e) {}}
void moveMethod(int param) {}
private void variableCall(TargetEnum target) {target.staticNested.doAction();}}}
void someMethod() {class LocalInner {void useInnerRec() {new StaticNested.InnerRec().moveMethod(TargetEnum.VALUE2);}}new LocalInner().useInnerRec();}}
protected enum TargetEnum extends ParentEnum {VALUE1, VALUE2;
static class StaticNested {class TargetInner {void moveMethod(TargetEnum param) {}void moveMethod(String param) {}}}
StaticNested staticNested = new StaticNested();
@Overridevoid moveMethod(TargetEnum param) {}}
class ParentEnum {void moveMethod(TargetEnum param) {}}