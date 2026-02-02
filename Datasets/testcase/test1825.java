package test;
import java.lang.reflect.Method;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
public class SourceClass {// Member inner classpublic class SourceInner {// Instance method in source inner classpublic int instanceMethod(TargetClass target) throws Exception {// Super keywordSystem.out.println("Super class: " + super.getClass().getSimpleName());
// Variable call using target fields and methodsint value = target.instanceField + TargetClass.STATIC_FIELD;value += target.getCalculatedValue(5);
// Used by reflection (first occurrence)Method targetMethod = TargetClass.class.getMethod("getCalculatedValue", int.class);value += (int) targetMethod.invoke(target, 10);
// Used by reflection (second occurrence)Method staticMethod = TargetClass.StaticNested.class.getMethod("doubleValue", int.class);value += (int) staticMethod.invoke(new TargetClass.StaticNested(), value);
return value;}}
// Local inner classpublic void outerMethod() {class LocalInner {void useInnerClass() throws Exception {TargetClass target = new TargetClass();int result = new SourceInner().instanceMethod(target);System.out.println("Result: " + result);}}new LocalInner().useInnerClass();}}
// Annotation with attribute value using call method@Retention(RetentionPolicy.RUNTIME)@interface MethodSource {String value() default "OtherClass.createTarget("init")";}
// Target class extending ParentClassclass TargetClass extends ParentClass {int instanceField = 20;static int STATIC_FIELD = 10;
public int getCalculatedValue(int num) {return instanceField * num;}
// Static nested class in targetpublic static class StaticNested {public int doubleValue(int val) {return val * 2;}}}
class ParentClass {}
// Other class with overloaded methodsclass OtherClass {// Overloaded method 1private static TargetClass createTarget(String init) {return new TargetClass();}
// Overloaded method 2private static TargetClass createTarget(int code) {return new TargetClass();}}
@MethodSource // Uses call method in annotation attributeclass AnnotationUser {}