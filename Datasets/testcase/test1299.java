package test.refactoring.movemethod;
import java.sql.SQLException;import java.lang.reflect.Method;
// Target record classprotected record TargetClass(String targetField) {// Target feature: anonymous inner classpublic void targetMethod() {Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous inner class");}};anonymous.run();}
// For overload_exist featurepublic TargetClass(Integer num) {this(num.toString());}}
// Source record class (extends/implements/permits requires base types - using functional interface for simplicity)protected record SourceClass(String sourceField, TargetClass targetParam) implements Runnable permits SourceClass.NestedPermit {// Source feature: static nested classpublic static class StaticNestedClass {}
// Source feature: permits - nested permit classpublic static non-sealed class NestedPermit extends SourceClass {public NestedPermit() {super("permit", new TargetClass("permitTarget"));}
@Overridepublic void run() {}}
// Source inner class (method_position: source_inner)public class SourceInnerClass {/**
Method javadoc (conforms to method feature)
@return TargetClass instance
@throws SQLException (conforms to method feature)*/private TargetClass innerMethod() throws SQLException {// Feature: super constructor invocation (via outer class)super.toString();
// Feature: ConstructorInvocation (private modifier, same_package_others pos)TargetClass.AnonymousHelper helper = new TargetClass.AnonymousHelper();
// Feature: ClassName.field (from ConstructorInvocation target_feature)String fieldValue = TargetClass.AnonymousHelper.STATIC_FIELD;
// Feature: variable call (targetParam is target class parameter, sourceField is source variable)targetParam.targetMethod();System.out.println(sourceField);
// Feature: 3 StringLiteral expressions (conforms to numbers:3, modifier:public, exp:StringLiteral)String lit1 = "stringLiteral1";String lit2 = "stringLiteral2";String lit3 = "stringLiteral3";System.out.println(lit1 + lit2 + lit3);
// Feature: overload_exist (TargetClass has overloaded constructor)TargetClass overloaded = new TargetClass(123);
// Feature: used_by_reflection (simulated invocation)try {Method refMethod = TargetClass.class.getMethod("targetMethod");refMethod.invoke(targetParam);} catch (ReflectiveOperationException e) {throw new SQLException("Reflection error", e);}
// Feature: anonymous inner class (source class feature)Runnable innerAnonymous = new Runnable() {@Overridepublic void run() {System.out.println("Source inner anonymous class");}};innerAnonymous.run();
return overloaded;}}
// Helper class for ConstructorInvocation (same package, private constructor)static class AnonymousHelper {public static final String STATIC_FIELD = "staticFieldValue"; // ClassName.field feature
// Private constructor (ConstructorInvocation modifier: private)private AnonymousHelper() {}}
@Overridepublic void run() {}}
// Test class to verify contextpublic class MoveMethodTest5228 {public static void main(String[] args) throws SQLException {TargetClass target = new TargetClass("testTarget");SourceClass source = new SourceClass("testSource", target);SourceClass.SourceInnerClass inner = source.new SourceInnerClass();inner.innerMethod();}}