package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnot {}
non-sealed public class SourceClass permits SubSourceClass {// Member inner class (source_feature)class SourceInner {}
{// Anonymous inner class (source_feature)new Runnable() {@Overridepublic void run() {}};}
@MethodAnnotdefault List<String> methodToMove(TargetClass target) {super(); // Super keywords
// Constructor invocationTargetClass newTarget = new TargetClass();
List<String> results = new ArrayList<>();// For loop with sub_class instance method call: super.methodName(arguments)for (int i = 0; i < 1; i++) {SubSourceClass sub = new SubSourceClass();List<String> subResult = sub.instanceMethod(target);results.addAll(subResult);}
// Switch caseswitch (target.targetField.length()) {case 8 -> results.add("Length 8");default -> results.add("Default Length");}
// Assert statementassert target.targetField != null : "Target field must not be null";
// Variable call + contains target parameter (per_condition)target.toString();results.add(target.targetField);
return results;}}
class SubSourceClass extends SourceClass {// Sub_class instance methodpublic List<String> instanceMethod(TargetClass target) {List<String> subList = new ArrayList<>();subList.add(super.methodToMove(target).get(0));return subList;}}
strictfp class TargetClass {public String targetField = "targetValue"; // Source contains target's field (per_condition)
public void createLocalInner() {// Local inner class (target_feature)class TargetLocalInner {}}}