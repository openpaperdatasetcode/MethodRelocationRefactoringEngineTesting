package same;
import java.util.ArrayList;import java.util.List;import java.util.Arrays;
class SourceClass {static class StaticNested {}
class Inner {void handle(TargetClass.InnerRec innerRec) {switch (innerRec.type) {case 1:List<String> list1 = new HelperClass(innerRec.data, 10);List<String> list2 = new HelperClass(innerRec.data);break;default:break;}
Object obj1 = (String) innerRec.value;Object obj2 = (Integer) innerRec.count;
List<String> items = Arrays.asList("a", "b");int sum = items.stream().mapToInt(s -> this.calculate(s.length())).sum();}
private synchronized int calculate(int len) {return len * TargetClass.STATIC_FIELD;}}
void createLocal() {class LocalInner {void useTarget(TargetClass target) {}}}}
class HelperClass {HelperClass(String data, int num) {}HelperClass(String data) {}}
package same;
/**
Target class with anonymous inner class*/protected class TargetClass {static int STATIC_FIELD = 2;
class InnerRec {String data;int type;Object value;int count;
InnerRec() {Runnable anon = new Runnable() {public void run() {}};}}}
package same;
sealed abstract class SourceClass permits ConcreteSource {SourceClass() {Runnable anon = new Runnable() {public void run() {}};}
protected void process(TargetClass.InnerRec innerRec) {List rawList = new ArrayList();rawList.add(innerRec.getValue());
for (int i = 0; i < 3; i++) {int val = TargetClass.InnerRec.staticMethod(i);if (val < 0) break;rawList.add(val);}
innerRec.update();}
void createLocal() {class LocalInner {void call(TargetClass.InnerRec inner) {inner.process();}}}}
class ConcreteSource extends SourceClass {}
package same;
public abstract class TargetClass {class InnerRec {private int value;
int getValue() {return value;}
void update() {value++;}
void process() {class LocalInner {void compute() {}}}
static int staticMethod(int num) {return num * 2;}}}