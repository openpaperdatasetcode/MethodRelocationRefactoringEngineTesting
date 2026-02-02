package test.refactoring;
import java.util.List;
interface SourceInterface {default void sourceMethod() {class FirstLocalInner {private TargetInterface targetField = new TargetInterface() {@Overridepublic Object targetMethod() {return new Object();}};
protected Object overrideMethod() {Object result = targetField.targetMethod();variableCall(result);
TargetInterface.MemberInner inner = targetField.new MemberInner();inner.useInstanceMethod();
return result;}
private void variableCall(Object obj) {System.out.println(obj.toString());}}
class SecondLocalInner {void useOverride() {FirstLocalInner firstInner = new FirstLocalInner();Object data = firstInner.overrideMethod();}}
new SecondLocalInner().useOverride();}}
public interface TargetInterface {Object targetMethod();
class MemberInner {public void useInstanceMethod() {TargetInterface target = new TargetInterface() {@Overridepublic Object targetMethod() {return "TargetData";}};Object result = target.targetMethod();}}}
class SourceImpl implements SourceInterface {}
class TargetImpl implements TargetInterface {@Overridepublic Object targetMethod() {return List.of("a", "b", "c");}}