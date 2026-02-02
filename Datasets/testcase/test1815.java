package test;
import java.sql.SQLException;import java.util.ArrayList;import java.util.List;import java.util.function.Function;
public enum SourceEnum permits ExtendedSourceEnum {VALUE;
protected String outerProtected = "protectedValue";
@MyAnnotationList<String> instanceMethod(TargetEnum target) throws SQLException {List<String> result = new ArrayList<>();
// ConstructorInvocation with private modifier using 3 target inner fieldsprivate class PrivateConstructor {PrivateConstructor(TargetEnum.TargetInner inner) {String data = inner.this.field1 + inner.this.field2 + inner.this.field3;result.add(data);}}new PrivateConstructor(target.new TargetInner());
// Instance method in Lambda expressionsFunction<TargetEnum.TargetInner, Object> lambda = inner -> inner.instanceMethod();result.add(lambda.apply(target.new TargetInner()).toString());
// Expression statementresult.add(target.new TargetInner().field1);
// Access outer protectedresult.add(outerProtected);
// With bounds (type parameter with bound)class BoundedType<T extends TargetEnum.TargetInner> {void addValue(T t) {result.add(t.field2);}}new BoundedType<>().addValue(target.new TargetInner());
// Variable call and property assignment with call methodTargetEnum.TargetInner inner = target.new TargetInner();inner.prop = TargetEnum.staticMethod(new TargetEnum.TargetInner());
return result;}
// Member inner classpublic class MemberInner {void useInstanceMethod(TargetEnum target) throws SQLException {new SourceEnum.VALUE.instanceMethod(target);}}
// Anonymous inner class{Runnable runner = new Runnable() {@Overridepublic void run() {try {instanceMethod(TargetEnum.TARGET);} catch (SQLException e) {}}};runner.run();}}
enum ExtendedSourceEnum extends SourceEnum {}
enum TargetEnum {TARGET;
public class TargetInner {String field1 = "f1";String field2 = "f2";String field3 = "f3";String prop;
public Object instanceMethod() {return super.toString() + field1;}}
public static final void staticMethod(TargetInner inner) {inner.prop = "assigned";}}
@interface MyAnnotation {}
