package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
interface MyInterface {}
abstract class SourceClass implements MyInterface {private int outerPrivate = 10;static class StaticNested {}class MemberInner {}
@MyAnnotationprivate void process(TargetClass target) {TargetClass targetInstance = new TargetClass();TargetClass.StaticNested staticNested = new TargetClass.StaticNested();
int count = 0;do {target.field += SourceClass.this.outerPrivate;count++;} while (count < TargetClass.STATIC_FIELD);
new MemberInner();System.out.println(target.field);}}
public class TargetClass extends ParentClass {public static final int STATIC_FIELD = 3;int field;
static class StaticNested {}}
class ParentClass {}