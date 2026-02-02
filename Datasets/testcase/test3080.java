import java.util.function.Supplier;
public class SourceClass {static class Nested {record InnerRecord(TargetClass target) {protected void process() {class LocalInner {protected abstract int compute();
private int increment(int val) {return ++val;}}
LocalInner local = new LocalInner() {@Overrideprotected int compute() {return TargetClass.staticField;}};
Supplier<Integer> supplier = LocalInner::compute;int result = supplier.get();
int a = 0, b = 0, c = 0;result += ++a;result += ++b;result += ++c;
do {target.innerMethod();if (result > 10) {return;}result = local.increment(result);} while (result < 20);}}}
{Runnable r = new Runnable() {public void run() {new Nested.InnerRecord(new TargetClass()).process();}};}}
/**
Javadoc for TargetClass
Contains static field and inner method for processing*/public class TargetClass {public static int staticField = 5;
void innerMethod() {}}