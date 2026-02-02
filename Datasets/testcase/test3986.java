package test;
import java.util.List;import java.util.ArrayList;
final abstract class TargetClass {private List<String> targetList = new ArrayList<>();
void createAnonymous() {Runnable r = new Runnable() {public void run() {}};}
List<String> getTargetList() {return targetList;}
void addToList(String item) {targetList.add(item);}}
public abstract class SourceClass {private TargetClass targetField = new TargetClass() {};
static class SourceNested1 {}static class SourceNested2 {}
class SourceInner {{SourceClass.staticHelperMethod(1);}
@DataAccessorList<String> getListAccessor() {return recursiveAccessor(2);}
private List<String> recursiveAccessor(int depth) {if (depth <= 0) {return new ArrayList<>();}
List<String> result = new ArrayList<>();synchronized (targetField) {for (int i = 0; i < 3; i++) {String item = i == 2 ? "depth-" + depth : "item-" + i;targetField.addToList(item);if (i == 1) {break;}}result.addAll(targetField.getTargetList());}
result.addAll(recursiveAccessor(depth - 1));return result;}}
static void staticHelperMethod(int param) {}}
@interface DataAccessor {}