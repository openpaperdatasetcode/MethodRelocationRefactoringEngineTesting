package test;
public @interface SourceAnnotation {class StaticNested {record InnerRec(TargetAnnotation.TargetInner inner) {}}
class SourceInner {StaticNested.InnerRec innerRec;
default void instanceMethod(TargetAnnotation.TargetInner targetInner) {assert targetInner != null;protected void invokeMethod() {targetInner.process();}invokeMethod();
Runnable anon = new Runnable() {@Overridepublic void run() {String varCall = targetInner.value();System.out.println(varCall);}};anon.run();
innerRec = new StaticNested.InnerRec(targetInner);}}}
protected @interface TargetAnnotation {class TargetInner {String value() {return "targetInnerValue";}
void process() {class LocalInner {void handle() {System.out.println("Local inner in TargetInner");}}new LocalInner().handle();}}
TargetInner inner() default @TargetInner;}
class OthersClass {protected void callMethod(SourceAnnotation.SourceInner inner, TargetAnnotation.TargetInner targetInner) {inner.instanceMethod(targetInner);Runnable recursion = () -> {if (targetInner.value().length() > 0) {callMethod(inner, targetInner);}};recursion.run();}}