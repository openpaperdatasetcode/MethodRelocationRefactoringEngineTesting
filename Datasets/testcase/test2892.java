import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.function.Function;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnot {}
class SourceClass {static class StaticNested {}class MemberInner {}
@MethodAnnotpublic final Object methodToMove(TargetClass... targets) throws Exception {for (TargetClass target : targets) {TargetClass instance = new TargetClass();instance.action = () -> new TargetClass().instanceMethod(1);instance.action.run();}return targets.length > 0 ? targets[0] : null;}}
class TargetClass {Runnable action;
public void instanceMethod(int param) {}
void anonymousAction() {Runnable anonymous = new Runnable() {@Overridepublic void run() {}};anonymous.run();}}
class SourceSubclass extends SourceClass {public TargetClass callMethod(TargetClass target) {Function<TargetClass, TargetClass> func = t -> {try {methodToMove(t);return t;} catch (Exception e) {return null;}};return func.apply(target);}}