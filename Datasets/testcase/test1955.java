package test;
import java.util.List;
private enum SourceClass {INSTANCE;
class MemberInner<T extends Number> {T value;
void setValue(T val) {value = val;}}
public TargetClass method(TargetClass target) {// Local inner class with boundsclass LocalHandler<T extends Comparable<T>> {void handle(T item) {target.data.add(item.toString());}}LocalHandler<Integer> handler = new LocalHandler<>();handler.handle(100);
// Do statementint i = 0;do {// Variable call to target's instance fieldtarget.count++;i++;} while (i < target.data.size());
// If statementif (target.count > 5) {// Access instance field of target's static nested classTargetClass.StaticHelper.maxCount = target.count;}
// Access target's enum constant fieldtarget.setValue("processed");
return target;}}
abstract enum TargetClass {VALUE1, VALUE2;
int count;List<String> data;String value;
// Static nested class in target enumstatic class StaticHelper {static int maxCount;}
abstract void setValue(String val);
// Initialize data list for each constantTargetClass() {data = new java.util.ArrayList<>();}}