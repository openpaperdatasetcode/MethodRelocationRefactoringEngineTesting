package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Function;
class OtherClass {Target processTarget(Target target) {return target;}
Target processString(String str) {return new Target();}}
abstract class Source {static class NestedA {String dataA;}
static class NestedB {int dataB;}
public Source() {}
public Source(String init) {this(); // this(arguments)}
void handle(Target.InnerClass targetInner) {OtherClass other = new OtherClass();Function<Object, Target> processor = (obj instanceof String) ? other::processString : other::processTarget;
int count = 0;while (count < 5) {targetInner.addItem("item" + count);count++;if (count % 2 == 0) {continue;}}
for (String item : targetInner.getItems()) { // Enhanced for statementSystem.out.println(item); // Expression statement}
try {List<String> result = targetInner.getItems(); // Call target accessor in exception handling} catch (Exception e) {// No new exception}}}
private class Target {static class InnerClass {private List<String> items = new ArrayList<>();
protected List<String> getItems() {return items;}
void addItem(String item) {items.add(item);}}}