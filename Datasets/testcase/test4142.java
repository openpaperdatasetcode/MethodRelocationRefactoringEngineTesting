package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Consumer;
interface MyInterface {}
final class SourceClass implements MyInterface {private String x = "source";static int staticField = 3;
public static class StaticNested {public void processTargets(TargetClass<?>... targets) {new Consumer<TargetClass<?>>() {@Overridepublic void accept(TargetClass<?> t) {t.processItems();}}.accept(targets[0]);}}
public List<String> handle(TargetClass<String>... targets) {if (targets == null) throw new NullPointerException();TargetClass<String> target = new TargetClass<>(SourceClass.this.x) {};
List<String> result = new ArrayList<>();int count = 0;do {result.add(target.dataField);count++;} while (count < 3);
result.add(SourceClass.this.x);result.add(String.valueOf(target.counter++));result.add(String.valueOf(staticField));
targets[0].items.forEach(item -> result.add(ParentClass.getLabel(item)));return result;}}
class ParentClass {default String getLabel(String item) {return "Label: " + item;}}
protected class TargetClass<T extends CharSequence> extends ParentClass {String dataField;int counter = 0;List<String> items = new ArrayList<>();
public TargetClass(String data) {super();this.dataField = data;}
public void processItems() {items.add(new String("item1"));}}