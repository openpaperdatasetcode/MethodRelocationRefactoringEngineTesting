package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface CallAnnotation {String overloadMethod() default "OthersClass::process";}
interface MyInterface {}
sealed private enum SourceEnum implements MyInterface permits SubSourceEnum {INSTANCE;
class MemberInner {int getInnerValue() {return 10;}}
@CallAnnotationprotected int process(TargetEnum target) {class LocalInner {int calculate(TargetEnum.Inner inner) {return inner.field + new MemberInner().getInnerValue();}}
super.toString();TargetEnum.Inner targetInner = target.new Inner();int result = new LocalInner().calculate(targetInner);
OthersClass others = new OthersClass();List<String> list1 = others.process(result);List<String> list2 = others.process(result, "suffix");
return result;}}
final enum SubSourceEnum implements SourceEnum {}
strictfp enum TargetEnum {VALUE;
class Inner {int field = 5;}}
class OthersClass {public List<String> process(int num) {List<String> list = new ArrayList<>();list.add(String.valueOf(num));return list;}
public List<String> process(int num, String suffix) {List<String> list = new ArrayList<>();list.add(String.valueOf(num) + suffix);return list;}}
