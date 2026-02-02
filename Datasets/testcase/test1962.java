package source;
import target.TargetClass;import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
public sealed class SourceClass permits SubSourceClass {static class StaticNested {static int staticField = 3;
Object overloadMethod() {return "overload 1";}
Object overloadMethod(int num) {return "overload 2: " + num;}
Object overloadMethod(String str) {return "overload 3: " + str;}}
class MemberInner {TargetClass.Inner.InnerRec getInnerRec(TargetClass target) {return target.new Inner().new InnerRec();}}
/**
Method to process target's inner recursive class
@param target the target class instance
@return processed object
@throws Exception if reflection fails*/Object method(TargetClass target) throws Exception {// Raw type usageList rawList = new ArrayList();rawList.add(target.field);
// Access static field dependencytarget.innerCount = StaticNested.staticField;
// Variable call to target's inner recursive classTargetClass.Inner.InnerRec innerRec = new MemberInner().getInnerRec(target);rawList.add(innerRec.data);
// Overloading methods in lambda (3 overloads)StaticNested nested = new StaticNested();Runnable lambda = () -> {Object res1 = nested.overloadMethod();Object res2 = nested.overloadMethod(1);Object res3 = nested.overloadMethod("test");innerRec.data = res1 + res2 + res3;};lambda.run();
// Assert statementassert innerRec.count == 3 : "Count mismatch";
// Reflection usageMethod method = TargetClass.Inner.InnerRec.class.getMethod("increment");method.invoke(innerRec);
return rawList;}}
class SubSourceClass extends SourceClass {}
package target;
strictfp class TargetClass extends ParentClass {String field;int innerCount;
class Inner {class InnerRec {String data;int count = 3;
void increment() {count++;// Local inner classclass Counter {int getValue() {return count;}}data = "Count: " + new Counter().getValue();}}}}
class ParentClass {protected int parentField;}