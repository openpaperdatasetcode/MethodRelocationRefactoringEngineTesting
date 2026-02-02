package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
sealed public class SourceClass<T> permits SubSourceClass {static class StaticNested {}
class MemberInner {protected List<String> varargsMethod(AbstractTargetClass<Integer>.StaticNested... targets) {class LocalInner {}
// Access target fieldfor (AbstractTargetClass<Integer>.StaticNested target : targets) {String fieldVal = target.targetField;}
// Uses outer thisSourceClass<T> outerThis = SourceClass.this;
// Throw statementif (targets.length == 0) {throw new IllegalArgumentException("No targets provided");}
// Expression statementint expr = targets.length * 2;variableCall();
// Call static method in functional interfaceSupplier<Object> supplier = () -> new SourceClass.StaticNested().publicStaticMethod();supplier.get();
return new ArrayList<>();}
private void variableCall() {}}
public static Object publicStaticMethod() {return new Object();}}
final class SubSourceClass extends SourceClass<String> {}
abstract class AbstractTargetClass {
static class StaticNested {
String targetField;
}
}
