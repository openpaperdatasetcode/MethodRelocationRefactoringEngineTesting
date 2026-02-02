package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Consumer;
abstract class SourceClass {public abstract List<String> overridingMethod(TargetClass.TargetInner targetInnerParam);}
public class TargetClass extends SourceClass {@Overridepublic List<String> overridingMethod(TargetClass.TargetInner targetInnerParam) {class LocalType {}LocalType local = new LocalType();
targetInnerParam.doSomething();List<String> result = new ArrayList<>();
OtherPackageClass.staticTryMethod(this, targetInnerParam);return result;}
class TargetInner {void doSomething() {}}
void callMethod() {TargetInner inner = new TargetInner();for (int i = 0; i < 5; i++) {Consumer<TargetInner> consumer = (t) -> overridingMethod(t);consumer.accept(inner);}}}
package other;
import test.TargetClass;import java.util.List;
public class OtherPackageClass {static void staticTryMethod(TargetClass target, TargetClass.TargetInner inner) {try {List<String> list = target.overridingMethod(inner);} catch (Exception e) {}}}