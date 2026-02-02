package source;
import target.TargetClass;import java.util.List;
private class SourceClass<T extends CharSequence> {private TargetClass targetField = new TargetClass();
public abstract List<String> processTarget(T... inputs);
class SourceInner {private List<String> fetchTargetData() {return SourceClass.this.targetField.getItems();}
void useAbstractMethod() {SourceClass<String> source = new SourceClass<>() {@Overridepublic List<String> processTarget(String... inputs) {List<String> targetItems = fetchTargetData();for (String input : inputs) {targetItems.add(input);}return targetItems;}
// Override violation: No superclass method to override intentionallypublic String toString() {return "SourceSubclass";}};source.processTarget("input1", "input2");}}
static class StaticNested {void callTargetStatic() {TargetClass target = new TargetClass();int i = 0;while (i < 3) {String data = TargetClass.getStaticData();target.addItem(data);i++;}}}}
package target;
import java.util.ArrayList;import java.util.List;
class TargetClass {private List<String> items = new ArrayList<>();
public List<String> getItems() {class LocalHelper {List<String> collect() {return new ArrayList<>(items);}}return new LocalHelper().collect();}
public void addItem(String item) {items.add(item);}
protected static String getStaticData() {return "static-data";}}