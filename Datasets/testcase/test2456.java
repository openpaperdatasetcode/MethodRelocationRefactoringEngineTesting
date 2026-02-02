package test.refactoring.movemethod;
import other.DiffPackageProcessor;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.function.Supplier;
@Retention(RetentionPolicy.RUNTIME)@interface CallAnnotation {String value() default "";}
interface DataProcessor<T> {T process(T data);}
public class TargetClass<T> implements DataProcessor<T> {public static String staticField = "target_static";
public class TargetInner {
private U value;
public TargetInner(U value) {this.value = value;}
public U getValue() {return value;}
public TargetClass<T> createTarget(T data) {return new TargetClass<>();}
private TargetClass<T> handleCase1() {return new TargetClass<>();}
private TargetClass<T> handleCase2() {return new TargetClass<>();}
private TargetClass<T> handleCase3() {return new TargetClass<>();}}
@Overridepublic T process(T data) {return data;}}
class SubTargetClass<T> extends TargetClass<T> {@Overridepublic T process(T data) {return super.process(data);}
private void abstractMethodCall(TargetClass<T> target) {target.process(null);}}
package other;
import test.refactoring.movemethod.TargetClass;
public class DiffPackageProcessor {public static <T> void process(TargetClass<T>.TargetInner<?> inner) {// VariableDeclarationStatement with ClassName.fieldprivate String fieldValue = TargetClass.staticField;System.out.println("Processed: " + fieldValue);}}
package test.refactoring.movemethod;
import other.DiffPackageProcessor;
public class SourceClass<T> {private T outerField;
public SourceClass(T outerField) {this.outerField = outerField;}
public class SourceInner {public class NestedInner {@strictfppublic int process(TargetClass<T> target, String param) {// Constructor invocationTargetClass<T>.TargetInner<Integer> inner = target.new TargetInner<>(100);
// Local inner classclass ValueHandler {int adjust(int value) {return value + 5;}}int adjusted = new ValueHandler().adjust(inner.getValue());
// Variable callObject varCall = inner.getValue();
// OuterClass.this.xT outerValue = SourceClass.this.outerField;
// Depends on inner classTargetClass<T> newTarget = inner.createTarget(outerValue);
// Switch statement with inner class normal methods (3)TargetClass<T> resultTarget;switch (param) {case "case1":resultTarget = inner.handleCase1();break;case "case2":resultTarget = inner.handleCase2();break;case "case3":resultTarget = inner.handleCase3();break;default:resultTarget = newTarget;}
// Different package processingDiffPackageProcessor.process(inner);
// Annotation attribute with sub class abstract method call@CallAnnotation(value = "processed")Supplier<Void> annotationCall = () -> {new SubTargetClass<T>().abstractMethodCall(resultTarget);return null;};annotationCall.get();
return adjusted;}}}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3149 {@Testpublic void testInstanceMethod() {SourceClass<String> source = new SourceClass<>("test_data");SourceClass<String>.SourceInner inner = source.new SourceInner();SourceClass<String>.SourceInner.NestedInner nested = inner.new NestedInner();
TargetClass<String> target = new TargetClass<>();int result = nested.process(target, "case1");assertEquals(105, result);}}