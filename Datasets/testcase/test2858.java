package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
abstract class SourceClass {public static String staticField = "sourceStatic";
class MemberInner {}
public static class StaticNested {}
public List<String> methodToMove(TargetClass target) {// ExpressionStatement with target_featuretarget.superField = 2;
// Variable callString val = SourceClass.staticField;// Depends on static fieldList<String> result = new ArrayList<>();result.add(val);
try {// Instance method (lambda) in exception handling statementsRunnable lambda = () -> {protectedInstanceMethod(3);};lambda.run();} catch (Exception e) {}
return result;}
// Overload existspublic List<String> methodToMove(String param) {return new ArrayList<>();}
protected Object protectedInstanceMethod(int num) {return num;}}
public class TargetClass extends SuperClass implements Runnable {class TargetMemberInner {}
@Overridepublic void run() {}}
class SuperClass {int superField;}