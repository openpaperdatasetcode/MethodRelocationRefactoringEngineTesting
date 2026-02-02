package other;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface TargetAnnot {}
public record TargetClass(String value) extends ParentClass {@TargetAnnotclass TargetInner {String getValue() {return value;}}}
class ParentClass {protected int parentField = 5;}
package test.refactoring.movemethod;
import other.TargetClass;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnot {}
record SourceClass(String name) {static class StaticNested {}
class SourceInner {@MethodAnnotint process(TargetClass... targets) {if (targets == null || targets.length == 0) {return 0;}
TargetClass.TargetInner inner = targets[0].new TargetInner();Object varCall = inner.getValue();int sum = 0;
assert targets.length > 0 : "No targets provided";
for (TargetClass target : targets) {if (target.value().length() > 3) {sum += target.value().length();}sum += super.parentField;}
Runnable printer = () -> System.out.println(this.name);printer.run();
return sum;}}}
import org.junit.Test;import static org.junit.Assert.*;import other.TargetClass;
public class MoveMethodTest3074 {@Testpublic void testVarargsMethod() {SourceClass source = new SourceClass("source");SourceClass.SourceInner inner = source.new SourceInner();TargetClass target1 = new TargetClass("test");TargetClass target2 = new TargetClass("long");
int result = inner.process(target1, target2);assertEquals(18, result);}}