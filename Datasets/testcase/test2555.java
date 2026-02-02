package same;
import java.io.IOException;import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface ProcessAnnotation {String value() default "processed by " + ParentClass.DEFAULT_NAME;}
public class SourceClass {private int outerPrivate = 100;static class StaticNested {}class MemberInner {}
@ProcessAnnotationprivate int calculate(TargetClass target) throws IOException {// SuperConstructorInvocation with target's this.fieldclass SubTarget extends TargetClass {SubTarget() {super(target.this.dataField);}}
// Lambda expressionRunnable task = () -> System.out.println("Processing: " + target.dataField);task.run();
// Recursion in parent class referenced in annotationList<String> items = ParentClass.fetchItems(target, 3);target.dataField += items.size();
// Call method from others_class in do-whileint i = 0;do {TargetClass result = Helper.createInnerInstance(target).compute();i++;} while (i < 2);
return target.dataField + outerPrivate;}}
class ParentClass {static final String DEFAULT_NAME = "parent";
static <T extends TargetClass> List<String> fetchItems(T target, int depth) {List<String> items = new ArrayList<>();if (depth <= 0) return items;items.add(String.valueOf(target.dataField));items.addAll(fetchItems(target, depth - 1)); // Recursionreturn items;}}
class Helper {final static TargetClass createInnerInstance(TargetClass outer) {return outer.new Inner().getTarget();}}
package same;
protected class TargetClass {int dataField = 5;
TargetClass(int initial) {this.dataField = initial;}
class Inner {Inner() {Runnable anon = new Runnable() {public void run() {}};}
TargetClass getTarget() {return new TargetClass(dataField + 10);}
TargetClass compute() {return new TargetClass(dataField * 2);}}}