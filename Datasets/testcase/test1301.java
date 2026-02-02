package source.pkg;
import target.pkg.TargetClass;import java.util.ArrayList;import java.util.Collection;
/**
Source class with member inner class and static nested class*/public class SourceClass {// Static nested class (source feature)public static class StaticNestedClass {}
// Member inner class (source feature)public class MemberInnerClass {}
/**
Javadoc for method to be refactored (method javadoc feature)
@param targetParam target class parameter (per_condition)
@return TargetClass instance (TargetClass Type return)*/public TargetClass processTarget(TargetClass targetParam) {// Expression statementtargetParam.setData("processed");
// Variable call + raw_type (raw Collection)Collection rawCollection = new ArrayList();rawCollection.add(targetParam);VariableHelper helper = new VariableHelper();helper.process(rawCollection);
// Collection operations with abstract default method (lambda: (parameters) -> methodBody)rawCollection.forEach(item -> processAbstract(item));
try {// Call source default method in exception handling (call_method feature)String result = this.callInExceptionHandling(targetParam);targetParam.setExtraData(result);} catch (IllegalStateException e) {// no_new_exception (rethrow without wrapping)throw e;}
return targetParam;}
/**
Abstract default method (method feature)
@param item raw type parameter
@return TargetClass instance
*/
public abstract TargetClass processAbstract(Object item);
/**
Call method (source type, default modifier, instance, this.methodName(arguments))
@param target target class parameter
@return String result
*/
String callInExceptionHandling(TargetClass target) {
return "Processed: " + target.getData();
}
// Helper class for variable callprivate static class VariableHelper {public void process(Collection collection) {// Variable call implementation}}}
package target.pkg;
/**
Target class (private modifier, no target_feature)*/private class TargetClass {private String data;private String extraData;
// Inner recursive structure (target_inner_rec)public class TargetInnerClass {public TargetClass getOuterInstance() {return TargetClass.this;}}
public String getData() {return data;}
public void setData(String data) {this.data = data;}
public String getExtraData() {return extraData;}
public void setExtraData(String extraData) {this.extraData = extraData;}}
// Test classpackage test.pkg;
import source.pkg.SourceClass;import target.pkg.TargetClass;
public class MoveMethodTest5227 {public static void main(String[] args) {SourceClass source = new SourceClass();TargetClass target = new TargetClass();TargetClass result = source.processTarget(target);System.out.println(result.getData());}}