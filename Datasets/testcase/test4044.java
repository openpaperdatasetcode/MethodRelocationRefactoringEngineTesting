package source.pkg;
import target.pkg.TargetAnnotation;import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
public @interface SourceAnnotation {class StaticNested<T extends CharSequence> {private List<String> baseList = new ArrayList<>();
public synchronized List<String> processGeneric(T... items) {super.add(items);for (T item : items) {baseList.add(item.toString());}return baseList;}}
List<String> getValues();
default List<String> collectData() {StaticNested<String> nested = new StaticNested<>();List<String> result = nested.processGeneric("source", "data");
Supplier<SourceAnnotation> anon = new Supplier<>() {@Overridepublic SourceAnnotation get() {return SourceAnnotation.this;}};
TargetAnnotation.TargetInnerRec inner = TargetAnnotation.TargetInnerRec.instance();int count = 0;while (count < 3) {inner.recursiveAdd(result, "item-" + count);count++;}
switch (result.size()) {case 3:private String field1 = inner.field1;private String field2 = inner.field2;private String field3 = inner.field3;result.add(field1 + field2 + field3);break;default:break;}
return result;}
default SourceAnnotation self() {return this;}}
package target.pkg;
import java.util.List;
private @interface TargetAnnotation implements DataCollector {static class TargetInnerRec {public String field1 = "t1";public String field2 = "t2";public String field3 = "t3";private static TargetInnerRec instance = new TargetInnerRec();
private TargetInnerRec() {}
public static TargetInnerRec instance() {return instance;}
public synchronized void recursiveAdd(List<String> list, String item) {if (list.size() >= 5) {return;}list.add(item);recursiveAdd(list, item + "_rec");}}
@Overridedefault List<String> collect() {return TargetInnerRec.instance().collect();}}
interface DataCollector {List<String> collect();}
