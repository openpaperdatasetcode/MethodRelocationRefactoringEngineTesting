package test;
import java.util.List;
sealed abstract class SourceClass permits SourceSubClass {public class SourceInner {public class InnerRec {private TargetClass targetField;
public InnerRec(TargetClass target) throws Exception {this.targetField = target;
synchronized (this) {<T extends List & CharSequence> void boundedMethod(T input) {}boundedMethod((T) List.of("bounded"));
if (targetField == null) {throw new IllegalArgumentException("Target cannot be null");}
this.useTarget();return;}}
private void useTarget() {targetField.createAnonymous();System.out.println(targetField.targetValue);}}}
public static class SourceStaticNested {void staticMethod() {}}}
final class SourceSubClass extends SourceClass {}
protected class TargetClass {int targetValue = 10;
public void createAnonymous() {Runnable r = new Runnable() {@Overridepublic void run() {System.out.println(TargetClass.this.targetValue);}};r.run();}}