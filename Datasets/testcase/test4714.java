package test;
import java.util.function.Function;
sealed interface SourceInterface permits SubSource1, SubSource2 {TargetInterface<String> targetField = new TargetInterface<>() {};
private void processTarget(String... items) {TargetInterface<String> target = SourceInterface.targetField;
SubSource1 sub1 = new SubSource1();Object val1 = sub1.getName();int val2 = sub1.getCount();String val3 = sub1.getDetail(10);
int i = 0;do {target.setValue(items[i]);i++;} while (i < items.length);
for (String item : items) {String data = target.getValue();}
Function<TargetInterface<String>, String> func = TargetInterface::getValue;String result = func.apply(target);}
class FirstInner {void callProcess() {SourceInterface.processTarget("item1", "item2");}}
class SecondInner {void useTarget() {TargetInterface<String> target = SourceInterface.targetField;target.setValue("from-inner");}}}
final class SubSource1 implements SourceInterface {private String name = "sub1";private int count = 5;
public String getName() {return name;}
public int getCount() {return count;}
public String getDetail(int suffix) {return name + "-" + suffix;}}
final class SubSource2 implements SourceInterface {}
protected interface TargetInterface<T> {void setValue(T value);
T getValue();
default void useLocal() {class LocalHelper {T fetch() {return TargetInterface.this.getValue();}}LocalHelper helper = new LocalHelper();helper.fetch();}}