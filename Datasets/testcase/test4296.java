package source;
import target.Target;import java.util.concurrent.Callable;
abstract class Source permits SourceImpl {static class SourceStaticNested {}
Callable<Void> anonInner = new Callable<>() {@Overridepublic Void call() throws Exception {return null;}};
synchronized Source(Target.TargetInner.TargetInnerRecursive param) {super();Target target = new Target();Target.TargetInner inner = target.new TargetInner();
switch (param.recursiveField) {case 1:break;default:break;}
Object var = param;try {int fieldVal = param.recursiveField;} catch (Exception e) {}}}
final class SourceImpl extends Source {SourceImpl(Target.TargetInner.TargetInnerRecursive param) {super(param);}}
package target;
public class Target extends TargetSuper {static class TargetInner {class TargetInnerRecursive {public int recursiveField;}}}
class TargetSuper {}