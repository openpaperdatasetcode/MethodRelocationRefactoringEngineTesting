import java.util.ArrayList;import java.util.Collection;
interface ParentInterface {void process(TargetClass<?>... params);}
protected record SourceClass<T extends CharSequence>(String id) {static class StaticNested {record InnerRecord<T>(T data) {@Override@Deprecatedpublic final void process(TargetClass<T>... targets) {if (targets.length == 0) {return;}
int count = 0;Collection<T> collection = new ArrayList<>();TargetClass<T> firstTarget = new TargetClass<>(targets[0].data());
boolean flag = (targets.length > 1) ? true : false;if (flag) {count = this.processVarargs(targets);}
collection.add(firstTarget.data());firstTarget.action();
ParentInterface parent = firstTarget::process;Object[] array = {parent};}
protected int processVarargs(TargetClass<T>... targets) {return targets.length;}}}
class MemberInner {void useInnerRecord() {new StaticNested.InnerRecord<>("test").process(new TargetClass<>("vararg1"));}}}
public record TargetClass<T>(T data) implements ParentInterface {@Overridepublic void process(TargetClass<?>... params) {Runnable r = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class");}};r.run();}
void action() {}}