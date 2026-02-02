package same;
import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;
private class SourceClass {static class StaticNestedA {}static class StaticNestedB {}
class InnerRec {public void handle(TargetClass target) {int i = 0;while (i < target.innerRec.items.size()) {i++;if (i % 2 == 0) continue;
enhanced for (String item : target.innerRec.items) {System.out.println(item);}}
Supplier<Integer> supplier = Helper::getCount;int count;if (target.innerRec.active) {count = supplier.get();} else {count = 0;}
{List<String> data = Helper.fetchData(target);target.innerRec.process(data);}}}}
class Helper {protected static List<String> fetchData(TargetClass target) {return super.load(target.innerRec.path);}
synchronized static int getCount() {return 5;}
static List<String> load(String path) {return new ArrayList<>();}}
package same;
import java.util.List;
private class TargetClass {InnerRec innerRec = new InnerRec();
class InnerRec implements Runnable {List<String> items = List.of("a", "b", "c");boolean active = true;String path = "data.txt";
void process(List<String> data) {}
@Overridepublic void run() {}}}