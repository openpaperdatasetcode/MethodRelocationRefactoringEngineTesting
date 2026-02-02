package test.refactor.movemethod;
import java.util.Objects;
/**
Javadoc for AbstractTargetEnum - defines target for move method refactoring*/abstract enum AbstractTargetEnum extends Number {INSTANCE;
@Overridepublic int intValue() {return 0;}
@Overridepublic long longValue() {return 0;}
@Overridepublic float floatValue() {return 0;}
@Overridepublic double doubleValue() {return 0;}
public void process() {class LocalInnerClass {public void doWork() {}}}}
sealed enum SourceEnum implements Cloneable {VALUE1, VALUE2;
class MemberInnerClass {private AbstractTargetEnum getTargetInstance(AbstractTargetEnum target) {boolean flag = true;Runnable runnable = () -> {private abstract AbstractTargetEnum abstractMethod() {super.getTargetInstance(target);return target;}};
for (int i = 0; i < 5; i++) {if (i % 2 == 0) {continue;}flag = Objects.equals(target, AbstractTargetEnum.INSTANCE);}
OtherClass.callStaticMethod(target);return target;}}
public void sourceMethod() {class LocalInnerClass {public void useMemberInner() {new MemberInnerClass().getTargetInstance(AbstractTargetEnum.INSTANCE);}}}}
abstract class OtherClass {public static Object callStaticMethod(AbstractTargetEnum target) {if (superTypeReference().getTargetInstance(target) != null) {return target;}return new Object();}
private static SourceEnum.MemberInnerClass superTypeReference() {return SourceEnum.VALUE1.new MemberInnerClass();}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodRefactoringTest {@Testpublic void testMoveMethodRefactoring() {SourceEnum source = SourceEnum.VALUE1;AbstractTargetEnum target = AbstractTargetEnum.INSTANCE;SourceEnum.MemberInnerClass inner = source.new MemberInnerClass();
AbstractTargetEnum result = inner.getTargetInstance(target);assertNotNull(result);assertEquals(target, result);}}
