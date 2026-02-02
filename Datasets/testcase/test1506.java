package test;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;
protected class Target {class MemberInner {private List<String> items = new ArrayList<>();
protected List<String> addItem(String item) {items.add(item);return items;}
protected List<String> clearItems() {items.clear();return items;}
protected List<String> getItems() {return new ArrayList<>(items);}}}
class Source {private String outerPrivate = "private_data";
class MemberInner {Target.MemberInner targetInner;
MemberInner(Target.MemberInner inner) {this.targetInner = inner;}}
protected void process(Target target) {// Constructor invocationTarget.MemberInner targetInner = target.new MemberInner();MemberInner sourceInner = new MemberInner(targetInner);
// Access outer private fieldsourceInner.targetInner.addItem(outerPrivate);
// Anonymous inner classRunnable anonTask = new Runnable() {@Overridepublic void run() {sourceInner.targetInner.addItem("anon_data");}};anonTask.run();
// Synchronized statementsynchronized (this) {// Property assignment with 3 inner class instance methodsList<String> list1 = sourceInner.targetInner.addItem("item1");List<String> list2 = sourceInner.targetInner.addItem("item2");List<String> list3 = ((Target.MemberInner) sourceInner.targetInner).getItems();}
// Used by reflectiontry {Method method = Target.MemberInner.class.getMethod("clearItems");method.invoke(sourceInner.targetInner);} catch (Exception e) {// No new exception thrown}
// Return statementif (sourceInner.targetInner.getItems().isEmpty()) {return;}
sourceInner.targetInner.addItem("final_item");}}