package same;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
non-sealed abstract class Source permits SourceImpl1, SourceImpl2 {private int outerPrivateField = 15;static class SourceStaticNested1 {}static class SourceStaticNested2 {}
@MethodAnnotationprivate int overloadingMethod(Target targetParam) {class LocalType {int localVal = targetParam.targetInstanceField;}new LocalType();
Object var = targetParam;int accessOuterPrivate = this.outerPrivateField;int staticFieldVal = Target.targetStaticField;return staticFieldVal;}
@MethodAnnotationprivate int overloadingMethod(Target.TargetInner targetInnerParam) {class LocalType {int localVal = targetInnerParam.innerField;}new LocalType();
Object var = targetInnerParam;int accessOuterPrivate = this.outerPrivateField;int staticFieldVal = Target.targetStaticField;return staticFieldVal;}}
final class SourceImpl1 extends Source {}final class SourceImpl2 extends Source {}
abstract class Target extends TargetSuperClass {public static int targetStaticField = 25;public int targetInstanceField = 35;
class TargetInner {public int innerField = 45;}}
abstract class TargetSuperClass {}
