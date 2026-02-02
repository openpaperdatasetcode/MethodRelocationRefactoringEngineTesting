package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;import java.util.function.Function;
protected class SourceClass {public static class StaticNested {}
{// Anonymous inner classnew Runnable() {@Overridepublic void run() {TargetClass target = new TargetClass();varargsMethod(target, new TargetClass());}}.run();}
private TargetClass varargsMethod(TargetClass... targets) {// Type declaration statementTargetClass resultTarget = targets.length > 0 ? targets[0] : new TargetClass();
// Instance method in Lambda expressions with method referenceFunction<TargetClass, List<String>> lambda1 = TargetClass::getInnerList;Function<TargetClass, List<String>> lambda2 = t -> t.getInnerList();
// Variable callList<String> list1 = lambda1.apply(resultTarget);List<String> list2 = lambda2.apply(targets.length > 1 ? targets[1] : resultTarget);
// Super keywordSystem.out.println(super.getClass().getSimpleName());
// Override violation (calling final method as if overridden)String invalidCall = resultTarget.finalMethod();
// Used by reflectiontry {Method method = TargetClass.class.getMethod("getInnerList");List<String> reflectedList = (List<String>) method.invoke(resultTarget);list1.addAll(reflectedList);} catch (Exception e) {}
return resultTarget;}}
protected class TargetClass {private String field = "targetField";
{// Anonymous inner class in targetnew Runnable() {@Overridepublic void run() {System.out.println("Target anonymous inner class: " + field);}}.run();}
protected List<String> getInnerList() {return new ArrayList<>(List.of(field, "innerItem"));}
public final String finalMethod() {return "Final method: " + field;}}