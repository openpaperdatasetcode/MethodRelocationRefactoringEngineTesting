package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
public record SourceRecord<T>(T data) {// Static nested class (source_feature)public static class StaticNestedSource {}
{// Anonymous inner class (source_feature)new Runnable() {};}
// Overloading method 1public List<String> methodToMove(TargetRecord.TargetInner.TargetInnerRec param) {super();
// Enhanced for statementfor (String str : param.getInnerList()) {// Variable call + access instance methodparam.toString();param.innerMethod();// Expression statementstr.toUpperCase();// Empty statement;}
// Depends on inner classTargetRecord.TargetInner.TargetInnerRec dependent = param;List<String> result = dependent.getInnerList();
// Used by reflectiontry {Method method = getClass().getMethod("methodToMove", TargetRecord.TargetInner.TargetInnerRec.class);method.invoke(this, param);} catch (Exception e) {}
return result;}
// Overloading method 2public List<String> methodToMove(TargetRecord.TargetInner.TargetInnerRec param, int num) {return new ArrayList<>(param.getInnerList());}}
public record TargetRecord(String value) {// Static nested class (target_feature)public static class StaticNestedTarget {}
class TargetInner {class TargetInnerRec {// Target field (source contains this field: per_condition)private final List<String> innerField = new ArrayList<>(List.of("targetFieldVal"));
public List<String> getInnerList() {return innerField;}
public void innerMethod() {}}}}