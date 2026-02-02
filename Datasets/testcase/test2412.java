package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
@interface TargetAnnotation {String value() default "";
class TargetNested {protected String superField = "base";}}
@interface SourceAnnotation {class SourceInner {List<String> process(TargetAnnotation.TargetNested target) {List<String> result = new ArrayList<>();TargetAnnotation.TargetNested nested = new TargetAnnotation.TargetNested();Object varCall = nested.superField;
// EmptyStatement featureif (((TargetAnnotation.TargetNested) target).superField.equals("1"));
// CastExpression with 3 castsObject obj1 = "string";Object obj2 = 42;Object obj3 = new Object();String str = (String) obj1;Integer num = (Integer) obj2;TargetAnnotation.TargetNested casted = (TargetAnnotation.TargetNested) obj3;
// Lambda expressionRunnable printer = () -> System.out.println(this);printer.run();
// Method reference in functional interfaceSupplier<List<String>> supplier = OtherClass::generateList;result.addAll(supplier.get());
return result;}
List<String> process(String str) {return new ArrayList<>();}}
// Local inner classclass LocalUsage {void use() {class LocalInner {LocalInner() {new SourceInner().process(new TargetAnnotation.TargetNested());}}new LocalInner();}}}
class OtherClass {protected static List<String> generateList() {List<String> list = new ArrayList<>();list.add("generated");return list;}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3078 {@Testpublic void testOverloadingMethod() {SourceAnnotation.SourceInner inner = new SourceAnnotation.SourceInner();TargetAnnotation.TargetNested target = new TargetAnnotation.TargetNested();List<String> result = inner.process(target);assertEquals(1, result.size());}}