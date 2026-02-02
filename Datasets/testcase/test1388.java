package test;
import java.io.IOException;import java.util.function.Supplier;
public class SourceClass<T> {private String outerPrivateField = "private";
static class StaticNested1 {}static class StaticNested2 {}
protected TargetClass methodToMove(TargetClass.TargetParam... params) { // contains target parameter (per_condition)// access_outer_privateString privateVal = outerPrivateField;
// super keywordssuper.toString();
// variable callStaticNested1 nested1 = new StaticNested1();StaticNested2 nested2 = new StaticNested2();
// IOExceptiontry {if (params == null) throw new IOException("Params null");} catch (IOException e) {}
// numbers:1, modifier:private, exp:LambdaExpressionSupplier<Integer> lambda = () -> 1;int num = lambda.get();
// call_method: sub_class, protected, static, OuterClass.InnerClass.methodName(), pos:functional interfacesSupplier<Object> callSupplier = () -> SubClass.StaticNested.methodFromInner();
return new TargetClass();}}
public class TargetClass implements Runnable {static class TargetParam {}
class MemberInner {} // member inner class (target_feature)
@Overridepublic void run() {} // implements (target_feature)}
class SubClass extends SourceClass<Object> {static class StaticNested {protected static Object methodFromInner() { // static, OuterClass.InnerClass.methodName()return new Object();}}}