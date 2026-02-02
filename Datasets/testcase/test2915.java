import java.lang.reflect.Method;import java.lang.annotation.ElementType;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.annotation.Target;import java.util.function.Supplier;
@Retention(RetentionPolicy.RUNTIME)@Target(ElementType.METHOD)@interface ReflectedAnnotation {String value() default "instanceReference::methodName";}
public class SourceClass<T> {class MemberInner {public abstract void methodToMove(TargetClass<?> target);
@ReflectedAnnotationprivate Object privateConstructor() {TargetSubclass subclass = new TargetSubclass("param");Supplier<Object> supplier = subclass::new;return supplier.get();}}
public void outerMethod(TargetClass<String> target) {class LocalInner {void labeledBlock() {labeledStmt: {System.out.println(target.innerField);TargetClass.MemberInner targetInner = target.new MemberInner();targetInner.doAction();}}}new LocalInner().labeledBlock();
try {Method method = MemberInner.class.getMethod("privateConstructor");} catch (NoSuchMethodException e) {}}}
protected class TargetClass {
String innerField;
class MemberInner {void doAction() {}}}
class TargetSubclass extends TargetClass<String> {public TargetSubclass(String innerField) {this.innerField = innerField;}}