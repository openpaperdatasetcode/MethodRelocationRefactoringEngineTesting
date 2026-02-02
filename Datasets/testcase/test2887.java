import java.lang.reflect.Method;
sealed class ParentPermitsClass permits SourceClass<T> {}
protected class SourceClass<T> extends ParentPermitsClass {class MemberInner {
protected TargetClass methodToMove (TargetClass... targets) {if (targets == null || targets.length == 0) {return new TargetClass ();}
for (TargetClass target : targets) {target.process();new SourceClass<T>.MemberInner().callMethod(target);}
try {Method method = TargetClass.class.getMethod("process");} catch (NoSuchMethodException e) {}
super.toString();return targets[0];}
protected TargetClass methodToMove(TargetClass target, T param) {return methodToMove(target);}
protected String callMethod(TargetClass target) {return new Helper(SourceClass.this::callMethod).result;}}
private String callMethod(TargetClass target) {return target.toString();}
class Helper {String result;Helper(StringSupplier supplier) {this.result = supplier.get();}}
@FunctionalInterfaceinterface StringSupplier {String get();}
Runnable anonymous = new Runnable() {@Overridepublic void run() {new MemberInner().methodToMove(new TargetClass());}};}
class TargetClass {void process() {class LocalInner {void useTarget() {}}new LocalInner().useTarget();}}