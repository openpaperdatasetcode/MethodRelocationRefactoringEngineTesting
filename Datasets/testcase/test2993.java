package test;
import java.lang.reflect.Method;import java.util.regex.Pattern;
// Protected source enum class (member inner class + static nested class)protected enum SourceEnum {INSTANCE;
// Static nested class (source feature)public static class SourceStaticNested {}
// Member inner class (method_position: source_inner)public strictfp class SourceInner {// Varargs method (strictfp access modifier, returns TargetClass Type)public strictfp TargetEnum varargsMethod(TargetEnum... targets) { // per_conditionif (targets == null || targets.length == 0) return null;
// Pattern (numbers=3, modifier=private)private Pattern pattern1 = Pattern.compile("\d+");private Pattern pattern2 = Pattern.compile("[a-zA-Z]+");private Pattern pattern3 = Pattern.compile("\s+");
// Labeled statementtargetLabel:for (TargetEnum target : targets) {// Assert statementassert target != null : "Target enum cannot be null";
// Expression statementtarget.targetMethod();
// Variable callTargetEnum.TargetInnerRec innerRec = target.new TargetInnerRec();innerRec.recursiveAction();
// ReturnStatement (public, target_feature: obj.field x2, pos: same_package_target)public String field1 = target.targetField1;public String field2 = target.targetField2;if (field1.equals("return")) break targetLabel;}
// Used by reflectiontry {Method method = TargetEnum.class.getMethod("targetMethod");method.invoke(targets[0]);} catch (Exception e) {// No new exception}
return targets[0];}}
// Member inner class (source feature)public class SourceMemberInner {public void helperMethod() {new SourceInner().varargsMethod(TargetEnum.VALUE1, TargetEnum.VALUE2);}}}
// Strictfp target enum class (anonymous inner class)strictfp enum TargetEnum {VALUE1("field1", "value1"),VALUE2("field2", "value2");
public final String targetField1;public final String targetField2; // obj.field for ReturnStatement
TargetEnum(String field1, String field2) {this.targetField1 = field1;this.targetField2 = field2;}
public void targetMethod() {// Anonymous inner class (target_feature)Runnable anon = new Runnable() {@Overridepublic void run() {}};anon.run();}
// Target inner recursive class (target_inner_rec)public class TargetInnerRec {public void recursiveAction() {}}}