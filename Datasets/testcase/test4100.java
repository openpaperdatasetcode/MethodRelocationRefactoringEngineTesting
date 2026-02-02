package test;
interface ParentSourceInterface {}
interface ParentTargetInterface {}
interface SourceInterface extends ParentSourceInterface {TargetInterface targetField = new TargetInterface() {};
class MemberInner {public void instanceMethod(List rawList) {int count = 0;while (count < 3) {TargetInterface.AnonymousHolder holder = new TargetInterface.AnonymousHolder();String var = holder.getTargetData();System.out.println(var);count++;}}
public void instanceMethod(String rawData) {instanceMethod(null);}}
default void createLocalInner() {class LocalInner {void callInnerMethod() {new MemberInner().instanceMethod("test");}}new LocalInner().callInnerMethod();}
private void callMethod() {new SourceInterface.MemberInner().instanceMethod(null);}}
protected interface TargetInterface extends ParentTargetInterface {class AnonymousHolder {public String getTargetData() {Runnable runnable = new Runnable() {@Overridepublic void run() {}};runnable.run();return "target_anonymous_data";}}}
