package test.refactoring.movemethod;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.function.Supplier;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnot {}
interface Processable {Object execute();}
public class TargetClass implements Processable {public String publicField;
public TargetClass(String field) {this.publicField = field;}
public class TargetInner {private int value;
public TargetInner(int value) {this.value = value;}
public TargetInner increment() {value++;return this;}
public Object getValue() {return value;}}
@Overridepublic Object execute() {return publicField;}}
class SubTargetClass extends TargetClass {public SubTargetClass(String field) {super(field);}
private Object chainMethod() {return new TargetInner(10).increment().getValue();}}
package other;
import test.refactoring.movemethod.TargetClass;
public class OtherPackageClass {public static TargetClass createTarget(String field) {return new TargetClass(field);}}
package test.refactoring.movemethod;
import other.OtherPackageClass;
protected class SourceClass {@MethodAnnotpublic final Object process(TargetClass target) {// Local inner classclass Processor {Object handle(TargetClass t) {return t.publicField;}}
// Anonymous inner classRunnable runner = new Runnable() {@Overridepublic void run() {System.out.println("Processing target: " + target.publicField);}};runner.run();
// Variable callObject varCall = target.publicField;
// Expression statementTargetClass.TargetInner inner = target.new TargetInner(5);
// ConstructorInvocation with obj.field in different packageTargetClass otherTarget = OtherPackageClass.createTarget(target.publicField + "_other");
// Labeled statementprocessLabel: {if (inner.getValue().equals(5)) {break processLabel;}varCall = "modified";}
// Functional interface with sub class chained method callSupplier<Object> supplier = SubTargetClass::new;SubTargetClass subTarget = (SubTargetClass) supplier.get();Object result = subTarget.chainMethod();
return result;}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3136 {@Testpublic void testInstanceMethod() {SourceClass source = new SourceClass();TargetClass target = new TargetClass("test");Object result = source.process(target);assertEquals(11, result);}}