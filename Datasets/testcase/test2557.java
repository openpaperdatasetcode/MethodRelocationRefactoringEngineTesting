package same;
import java.util.ArrayList;import java.util.List;
public class SourceClass {static class StaticNestedA {}static class StaticNestedB {}
@Deprecatedprotected void handle(TargetClass target) {// ConstructorInvocation with 3 target static fields in diff package (simulated with helper)TargetClass.InnerRec inner = new TargetClass.InnerRec(TargetClass.FIELD1,TargetClass.FIELD2,TargetClass.FIELD3);
; // Empty statement
// Varargs method in sub_class, called in array initializationTargetClass[] targets = {new SubTarget().addItems("item1", "item2")};
// Collection operation with sub_class static method callList<String> data = new ArrayList<>();SubTarget.processInner(target.new InnerRec(), data);
@SuppressWarnings("unused")boolean flag = inner.checkStatus(); // Access instance method}}
class SubTarget extends TargetClass {// Synchronized varargs method overriding super@Overridesynchronized TargetClass addItems(String... items) {super.addItems(items);return this;}
// Private static method for outer instance's inner classprivate static void processInner(InnerRec inner, List<String> data) {inner.append(data, "processed");}}
package same;
import java.util.List;
/**
Target class with member inner class and static fields/
private class TargetClass {
/* Static field 1 /
static final int FIELD1 = 10;
/* Static field 2 /
static final int FIELD2 = 20;
/* Static field 3 */static final int FIELD3 = 30;
/**
Member inner class for record processing*/class InnerRec {private int value1;private int value2;private int value3;
InnerRec(int v1, int v2, int v3) {this.value1 = v1;this.value2 = v2;this.value3 = v3;}
boolean checkStatus() {return value1 + value2 + value3 > 0;}
void append(List<String> list, String item) {list.add(item);}}
// Varargs method to be overriddenTargetClass addItems(String... items) {return this;}}