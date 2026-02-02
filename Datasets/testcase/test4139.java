package test;
import java.util.List;
public class SourceClass<T extends Number> {public class MemberInner {protected static void process(TargetClass target) throws Exception {class LocalInner {String data;LocalInner(String data) { this.data = data; }}
LocalInner local = new LocalInner("test");int value = target.counter;
synchronized (target.lock) {target.counter++;}
do {int result = calculate(value);value--;} while (value > 0);
List<String> list = target.innerClass.getItems();}
private abstract int calculate(int num);}
public abstract void setup();}
protected class TargetClass {int counter = 0;final Object lock = new Object();public MemberInnerClass innerClass = new MemberInnerClass();
public class MemberInnerClass {List<String> getItems() {return List.of("item1", "item2", "item3");}}}