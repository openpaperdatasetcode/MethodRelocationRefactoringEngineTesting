import java.lang.reflect.Method;import java.util.List;
interface Processable<T> {}
protected class SourceClass<T> implements Processable<T> {static class StaticNested {}
class MemberInner {void assist(TargetClass target) {target.processInner();}}
public Object process(TargetClass... targets) {try {for (TargetClass target : targets) {MemberInner inner = new MemberInner();inner.assist(target);
OtherClass.instanceMethod(target);
Method method = TargetClass.class.getMethod("processInner");method.invoke(target);}return targets.length > 0 ? targets[0] : null;} catch (Exception e) {return null;}}}
abstract class TargetClass {void processInner() {class LocalInner {void doWork() {}}new LocalInner().doWork();}}
class OtherClass {public static <T> void instanceMethod(TargetClass target) {target.processInner();}}