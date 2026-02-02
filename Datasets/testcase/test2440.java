package test.refactoring.movemethod;
import java.util.function.Supplier;
public abstract class TargetClass {public abstract class TargetInner {public abstract String getValue();}}
class ConcreteTarget extends TargetClass {@Overridepublic TargetInner new TargetInner() {return new TargetInner() {private String value = "concrete";
@Overridepublic String getValue() {return value;}};}
public TargetClass getTargetInstance() {return new ConcreteTarget();}}
protected abstract class SourceClass {class SourceInner1 {}class SourceInner2 {}
private void process(TargetClass target) {// Variable callTargetClass.TargetInner inner = target.new TargetInner();Object varCall = inner.getValue();
// If/else conditions with sub class overriding method referenceConcreteTarget concrete = new ConcreteTarget();Supplier<TargetClass> targetSupplier;
if (inner.getValue().isEmpty()) {targetSupplier = concrete::getTargetInstance;} else {targetSupplier = ConcreteTarget::new;}
TargetClass newTarget = targetSupplier.get();varCall = newTarget.new TargetInner().getValue();}
private void process(String str) {// Overloading methodTargetClass target = new ConcreteTarget();process(target);}}
import org.junit.Test;
public class MoveMethodTest3126 {@Testpublic void testOverloadingMethod() {SourceClass source = new SourceClass() {};// Testing through reflection since SourceClass is protectedtry {java.lang.reflect.Method method = SourceClass.class.getDeclaredMethod("process", String.class);method.setAccessible(true);method.invoke(source, "test");} catch (Exception e) {e.printStackTrace();}}}