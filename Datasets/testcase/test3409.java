package source;
import target.TargetClass;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
// Private source class (different package) with local and anonymous inner classesprivate class SourceClass {class InnerClass {class InnerRec {@MethodAnnotation // has_annotation// Final varargs method (position: source_inner_rec)final <T extends Number & Comparable<T>> void process(TargetClass... targets) { // with_bounds// Name expression (numbers=2, default modifier)default String name1 = "source_name1";default String name2 = "source_name2";
for (TargetClass target : targets) {// Constructor invocationTargetClass newTarget = new TargetClass();
// Private AssertStatement (target_feature: this.field=3)private boolean valid = target.this.field == 3;assert valid : "Target field must be 3";
variableCall(target);variableCall(newTarget);
// Local inner class (source_class feature)class LocalProcessor {void enhance(TargetClass t) {t.setField(t.getField() + 1);}}new LocalProcessor().enhance(target);
// Anonymous inner class (source_class feature)Runnable anonymousTask = new Runnable() {@Overridepublic void run() {System.out.println(name1 + "_" + name2);}};anonymousTask.run();}}
private void variableCall(TargetClass target) {target.doTask();}}}}
package target;
// Public target class with implements and local inner classpublic class TargetClass implements Processable {int field = 3; // this.field=3
public int getField() {return field;}
public void setField(int field) {this.field = field;}
public void doTask() {// Local inner class (target_feature)class LocalHelper {void validate() {if (field != 3) throw new IllegalArgumentException();}}new LocalHelper().validate();}}
interface Processable {}