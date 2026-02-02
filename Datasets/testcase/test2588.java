package test.refactoring.movemethod;
import java.lang.reflect.Method;import java.util.function.Supplier;
interface MyInterface {void perform();}
class ParentClass {public static String parentMethod() {return "parent";}}
class TargetClass {class TargetInner {class NestedInner {private String innerField;
private NestedInner(String field) {this.innerField = field;}
public String getField() {return innerField;}}}
{new Runnable() {@Overridepublic void run() {TargetInner inner = new TargetInner();inner.new NestedInner("init");}}.run();}}
class SourceClass extends ParentClass implements MyInterface {protected String outerProtected = "protected";
class SourceInner {class NestedInner implements MyInterface {@Overridepublic final void perform() {class LocalType {TargetClass.TargetInner.NestedInner create(TargetClass.TargetInner.NestedInner target) {return target;}}
TargetClass target = new TargetClass();TargetClass.TargetInner inner = target.new TargetInner();TargetClass.TargetInner.NestedInner nested = inner.new NestedInner("test");LocalType localVar = new LocalType();Object varCall = localVar.create(nested);
try {Method method = TargetClass.TargetInner.NestedInner.class.getMethod("getField");String value = (String) method.invoke(nested);System.out.println(value + outerProtected);} catch (Exception e) {// No new exception thrown}
Supplier<String> supplier = ParentClass::parentMethod;System.out.println(supplier.get());}}}
{SourceInner inner = new SourceInner();inner.new NestedInner().perform();}
@Overridepublic void perform() {}}
import org.junit.Test;
public class MoveMethodTest3038 {@Testpublic void testOverridingMethod() {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();SourceClass.SourceInner.NestedInner nested = inner.new NestedInner();nested.perform();}}