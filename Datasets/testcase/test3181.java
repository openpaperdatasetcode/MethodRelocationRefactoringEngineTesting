package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;import java.lang.reflect.Method;
// Interface for source enum's implements featureinterface EnumInterface {void interfaceMethod();}
// Source enum class (public modifier, implements + permits + member inner class + local inner class)public enum SourceEnum implements EnumInterface permits SourceEnum.SubEnum {INSTANCE;
private TargetEnum targetField = TargetEnum.SUB_INSTANCE; // Per condition: source contains target field
// Member inner class (source feature)public class SourceMemberInner {// Instance method feature (1, inner_class, instance, new ClassName().method(), pos: functional interfaces)public List<String> instanceFeatureMethod() {Supplier<List<String>> supplier = () -> new SourceMemberInner().process();return supplier.get();}
private List<String> process() {return new ArrayList<>(List.of("feature-result"));}}
// Local inner class (source feature)public void createLocalInner() {class SourceLocalInner {public void localMethod() {}}new SourceLocalInner().localMethod();}
@Overridepublic void interfaceMethod() {}
// Varargs method (private access modifier, returns TargetEnum Type)private TargetEnum varargsMethod(TargetEnum... targetParams) {// StringLiteral (numbers=2, modifier=public)public String literal1 = "string-literal-1";public String literal2 = "string-literal-2";
TargetEnum result = null;for (TargetEnum target : targetParams) {result = target;
// Super constructor invocation (target's parent enum implicit super)TargetEnum.TargetMemberInner inner = target.new TargetMemberInner();
// Variable calltarget.abstractMethod();inner.innerMethod();SourceMemberInner memberInner = new SourceMemberInner();List<String> featureResult = memberInner.instanceFeatureMethod();
// Target inner recursive class (target_inner_rec)TargetEnum.TargetInnerRec innerRec = target.new TargetInnerRec();innerRec.recursiveAction();
// Used by reflectiontry {Method method = TargetEnum.class.getMethod("abstractMethod");method.invoke(target);} catch (Exception e) {// No new exception}}
return result;}
// Permitted sub enum (source feature: permits)public static non-sealed enum SubEnum extends SourceEnum {SUB_INSTANCE;
private SubEnum() {super(); // Super constructor invocation}}}
// Abstract target enum class (abstract modifier, member inner class)abstract enum TargetEnum {SUB_INSTANCE;
public int targetField = 20; // Field for per_condition
public abstract void abstractMethod();
// Member inner class (target_feature)public class TargetMemberInner {public void innerMethod() {}}
// Target inner recursive class (target_inner_rec)public class TargetInnerRec {public void recursiveAction() {}}}