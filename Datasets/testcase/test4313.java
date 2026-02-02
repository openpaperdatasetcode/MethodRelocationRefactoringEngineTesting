package test.source;
import test.target.Target;
public interface Source {Target sourceTargetField = Target.INSTANCE;
static class SourceStaticNested1 {}static class SourceStaticNested2 {}
class SourceInner {class SourceInnerRec {public Object instanceMethod(int type) throws IllegalArgumentException {Object targetVar = sourceTargetField;
switch (type) {case 1:return targetVar.toString();case 2:return SourceStaticNested1.class;default:throw new IllegalArgumentException("Invalid type: " + type);}}}}}
package test.target;
public interface Target {Target INSTANCE = new Target() {};
static class TargetStaticNested {}}
package test;
import org.junit.Test;import test.source.Source;import test.target.Target;import static org.junit.Assert.*;
public class MoveMethodRefactoringTest {@Testpublic void testInstanceMethodBeforeMove() throws IllegalArgumentException {Source.SourceInner sourceInner = new Source.SourceInner();Source.SourceInner.SourceInnerRec innerRec = sourceInner.new SourceInnerRec();
Object result1 = innerRec.instanceMethod(1);assertEquals(Target.INSTANCE.toString(), result1);
Object result2 = innerRec.instanceMethod(2);assertEquals(Source.SourceStaticNested1.class, result2);
try {innerRec.instanceMethod(3);fail("Should throw IllegalArgumentException");} catch (IllegalArgumentException e) {assertEquals("Invalid type: 3", e.getMessage());}}
@Testpublic void testInstanceMethodAfterMove() throws IllegalArgumentException {Target.TargetStaticNested nested = new Target.TargetStaticNested();Target target = Target.INSTANCE;
Object result1 = ((MovedMethodTarget) target).instanceMethod(1, nested);assertEquals(target.toString(), result1);
Object result2 = ((MovedMethodTarget) target).instanceMethod(2, nested);assertEquals(Source.SourceStaticNested1.class, result2);
try {((MovedMethodTarget) target).instanceMethod(3, nested);fail("Should throw IllegalArgumentException");} catch (IllegalArgumentException e) {assertEquals("Invalid type: 3", e.getMessage());}}
private interface MovedMethodTarget extends Target {Object instanceMethod(int type, Target.TargetStaticNested nested) throws IllegalArgumentException;}}