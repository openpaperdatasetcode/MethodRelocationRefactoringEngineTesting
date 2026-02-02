import java.util.List;import java.lang.reflect.Method;import java.util.function.Supplier;
class SourceClass {static class StaticNested {record InnerRecord(TargetClass targetParam) {public void process() throws Exception {for (int i = 0; i < 5; i++) {if (i % 2 == 0) {continue;}
synchronized (this) {TargetClass.Inner targetInner = targetParam.new Inner();targetInner.doAction();
Supplier<List<String>> supplier = () -> OtherClass.staticMethod(targetInner);supplier.get();}}
Method method = TargetClass.Inner.class.getMethod("doAction");method.invoke(targetParam.new Inner());}}}
class MemberInner {void useInnerRecord() throws Exception {new StaticNested.InnerRecord(new TargetClass()).process();}}}
private class TargetClass implements ActionInterface {class Inner implements SuperTypeInterface {@Overridepublic void doAction() {// Inner class implementation}}}
interface ActionInterface {}interface SuperTypeInterface {void doAction();}
class OtherClass {public static final List<String> staticMethod(SuperTypeInterface superType) {superType.doAction();return List.of();}}
