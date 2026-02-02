package test;
import java.util.ArrayList;import java.util.List;
protected class SourceClass extends ParentSource {class MemberInner1 {class InnerRec<T extends CharSequence> {@CustomAnnotationprotected List<String> method(TargetClass target, T data) {// With type bounds usageList<T> boundedList = new ArrayList<>();boundedList.add(data);
// Synchronized statementsynchronized (target) {target.items.addAll(boundedList.stream().map(CharSequence::toString).toList());}
// this.var = var assignmentthis.dataHolder = data;
// Variable call to target's fieldtarget.counter += TargetClass.STATIC_OFFSET;
// Access target's local inner class functionalitytarget.processItems((item) -> item.length() > 3);
return target.items;}
private T dataHolder;}}
class MemberInner2 {}}
class ParentSource {}
public class TargetClass {static final int STATIC_OFFSET = 1;List<String> items = new ArrayList<>();int counter = 0;
void processItems(java.util.function.Predicate<String> filter) {// Local inner class in targetclass ItemProcessor {void filterAndAdd(String item) {if (filter.test(item)) {items.add(item);}}}
ItemProcessor processor = new ItemProcessor();items.forEach(processor::filterAndAdd);}}
@interface CustomAnnotation {}