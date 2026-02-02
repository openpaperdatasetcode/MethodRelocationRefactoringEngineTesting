package samepkg;
import java.io.IOException;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorTest {}
public record SourceClass<T extends Number>(TargetClass<T> targetField) { // Per condition: source contains target's fieldpublic static class StaticNested1 {}public static class StaticNested2 {}
public class SourceInnerClass {@RefactorTest // has_annotationpublic Object processTarget() {
// Super constructor invocation (anonymous class)
ParentClass parentInstance = new ParentClass() {};
// Labeled statementprocessLabel: {if (targetField.field1() == 0) {break processLabel;}}
// Synchronized statementsynchronized (this) {targetField.field2(targetField.field2() + 1);}
// Expression statementtargetField.field1(targetField.field1() * 2);
// Variable call + access_instance_fieldint fieldVal1 = targetField.field1();int fieldVal2 = targetField.field2();StaticNested1 nested1 = new StaticNested1();
// ConstructorInvocation (diff_package_others, 2 super.fields)OthersClass others = new OthersClass(parentInstance);others.accessSuperFields();
// Abstract method (property assignment position)TargetClass<T> abstractResult = new AbstractImpl().abstractMethod(targetField);
// Call parent class overloading method (method reference)parentInstance.execute(targetField::field1);parentInstance.execute(targetField::field2);
// Overload_exist (target has overloaded methods)targetField.process(fieldVal1);targetField.process(fieldVal1, fieldVal2);
return abstractResult;}}
// Abstract method for featuresprotected abstract static class AbstractBase {protected abstract <V extends Number> TargetClass<V> abstractMethod(TargetClass<V> target);}
protected static class AbstractImpl extends AbstractBase {@Overrideprotected <V extends Number> TargetClass<V> abstractMethod(TargetClass<V> target) {target.field1(target.field1() + 1); // property assignmentreturn target;}}}
package samepkg;
import java.io.IOException;
private record TargetClass(int field1, int field2) {
public void process(int val) {} // Overloaded method 1
public void process(int val1, int val2) {} // Overloaded method 2 (overload_exist)
public void doProcess() {// Local inner classclass LocalInner {void handleIOException() {try {throw new IOException("Test IO exception");} catch (IOException e) {// no_new_exception: catch and handlee.printStackTrace();}}}new LocalInner().handleIOException();}}
package samepkg;
public class ParentClass {protected int superFieldA = 10;protected int superFieldB = 20;
// Overloading methods for call_methodpublic void execute(Runnable task) {task.run();}
public void execute(IntSupplier supplier) {supplier.getAsInt();}
@FunctionalInterfacepublic interface IntSupplier {int getAsInt();}}
package otherpkg;
import samepkg.ParentClass;import samepkg.TargetClass;
public class OthersClass extends ParentClass {public OthersClass(ParentClass parent) {super();}
// ConstructorInvocation target: access 2 super.fieldspublic void accessSuperFields() {int sum = super.superFieldA + super.superFieldB;}}