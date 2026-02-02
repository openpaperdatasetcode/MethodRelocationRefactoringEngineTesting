package test;
import java.util.List;import java.util.ArrayList;
class SourceClass {static class StaticNested {}class MemberInner {}
/**
This method processes the target class and returns a list of strings.
It contains the field of the target class and requires exception handling.
@param target the target class instance
@return List<String> result list
@throws Exception if processing fails*/protected List<String> processTarget(TargetClass target) throws Exception {List<String> result = new ArrayList<>();int fieldVal = target.staticField; // Contains target's field
// Expression statement using target's static nested classTargetClass.StaticNested nested = new TargetClass.StaticNested();result.add(String.valueOf(nested.nestedField));
// Lambda expression with call to others_class's synchronized methodRunnable lambda = () -> {TargetClass returnedTarget = OthersClass.synchronizedStaticMethod(nested);result.add(returnedTarget.toString());};lambda.run();
return result;}}
class TargetClass {static int staticField; // Field used in source class
static class StaticNested {int nestedField;}}
class OthersClass {/**
Synchronized static method called from lambda in source class.
Uses super type reference in method call.
@param obj instance of TargetClass.StaticNested (subtype of Object)
@return TargetClass instance
*/
synchronized static TargetClass synchronizedStaticMethod(Object obj) {
return new TargetClass();
}
}