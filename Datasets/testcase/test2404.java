package other;
public class TargetClass {public static class TargetInner {public static class NestedInner {private String data;
public NestedInner(String data) {this.data = data;}
public String getData() {return data;}
public void setData(String data) {this.data = data;}}}}
package test.refactoring.movemethod;
import other.TargetClass;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnot {}
public class SourceClass {static class StaticNested {}
class MemberInner {}
private TargetClass.TargetInner.NestedInner getNested() {return new TargetClass.TargetInner.NestedInner("default");}
@MethodAnnotprivate TargetClass.TargetInner.NestedInner process(TargetClass target) {TargetClass.TargetInner inner = new TargetClass.TargetInner();TargetClass.TargetInner.NestedInner nested = inner.new NestedInner("initial");Object varCall = nested.getData();
class Base {Base() {}}class Derived extends Base {Derived() {super();}}Derived derived = new Derived();
assert nested.getData() != null : "Data should not be null";
loop: for (int i = 0; i < 3; i++) {nested.setData("loop_" + i);if (i == 1) {break loop;}}
return nested;}
private TargetClass.TargetInner.NestedInner process(String str) {return new TargetClass.TargetInner.NestedInner(str);}}
import org.junit.Test;import static org.junit.Assert.*;import other.TargetClass;
public class MoveMethodTest3066 {@Testpublic void testInstanceMethod() {SourceClass source = new SourceClass();TargetClass target = new TargetClass();TargetClass.TargetInner.NestedInner result = source.process(target);assertNotNull(result);assertEquals("loop_1", result.getData());}}