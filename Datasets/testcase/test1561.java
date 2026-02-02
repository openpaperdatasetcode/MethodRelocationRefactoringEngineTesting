package test;
import java.util.Arrays;import java.util.List;
public class SourceClass<T extends Number> {public class NestedInner {public TargetClass process() {public TargetClass target1 = new TargetClass();public TargetClass target2 = new TargetClass();public TargetClass target3 = new TargetClass();
target1.value = TargetClass.STATIC_FIELD1;target2.value = TargetClass.STATIC_FIELD2;target3.value = TargetClass.STATIC_FIELD3;
return target1;}}
public TargetClass process(TargetClass target) {NestedInner inner = new NestedInner();TargetClass result = inner.process();
; // empty statement
for (TargetClass t : Arrays.asList(target, result)) {t.action();}
return target;}}
sealed class TargetClass permits SubTarget {public static final int STATIC_FIELD1 = 1;public static final int STATIC_FIELD2 = 2;public static final int STATIC_FIELD3 = 3;
int value;
void action() {Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println(value);}};anon.run();}}
final class SubTarget extends TargetClass {}
class OthersClass {private void handleException() {try {// some operation} catch (Exception e) {TargetClass::new;}}}