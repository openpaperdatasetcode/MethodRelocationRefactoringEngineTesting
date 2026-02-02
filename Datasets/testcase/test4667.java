package test;
import java.util.function.Supplier;
enum SourceEnum {VALUE1, VALUE2;
static int staticField = 3;
class Inner {class InnerRec {synchronized void normalMethod(PrivateTargetEnum target) {class LocalType {}LocalType local = new LocalType();
Supplier<Integer> supplier = () -> {synchronized (this) {int count = 3;return target.innerRec.instanceMethod(count);}};
for (int i = 0; i < staticField; i++) {if (i == 1) {continue;}int result = supplier.get();}}}}
Runnable anon1 = new Runnable() {@Overridepublic void run() {}};
Runnable anon2 = new Runnable() {@Overridepublic void run() {}};}
private enum PrivateTargetEnum implements Runnable {TARGET1, TARGET2;
Inner inner = new Inner();
class Inner {InnerRec innerRec = new InnerRec();
class InnerRec {synchronized int instanceMethod(int val) {return val * 2;}}}
Runnable anon = new Runnable() {@Overridepublic void run() {}};
@Overridepublic void run() {}}
class SubClass extends SourceEnum.Inner.InnerRec {}
