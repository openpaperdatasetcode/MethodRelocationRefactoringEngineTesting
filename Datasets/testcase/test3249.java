package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Function;
public class TargetClass {public void targetMethod() {class TargetLocalInner {}new TargetLocalInner();}}
class SourceClass {private TargetClass targetField = new TargetClass();
class SourceMemberInner {}
/**
Javadoc for static target method
@param param1 first parameter
@param param2 second parameter
@return List of strings*/public final static List<String> staticMethodToMove(TargetClass targetParam, String param1, int param2) {class SourceLocalInner {}new SourceLocalInner();
super();assert targetParam != null : "Target parameter cannot be null";
List<String> result = new ArrayList<>();for (int i = 0; i < 3; i++) {TargetClass targetInstance = SourceClass.staticHelperMethod(targetField, param1, i);result.add(targetInstance.toString());}
Function<String, String> func1 = String::trim;Function<Integer, String> func2 = String::valueOf;
targetParam.targetMethod();targetField.targetMethod();
return result;}
public static TargetClass staticHelperMethod(TargetClass target, String str, int num) {SourceClass outer = new SourceClass();SourceClass.SourceMemberInner inner = outer.new SourceMemberInner();return new TargetClass();}
protected TargetClass(TargetClass target, Runnable runnable) {SourceClass.staticMethodToMove(target, "test", 10);runnable = () -> SourceClass.staticMethodToMove(targetField, "lambda", 20);}}