package test;
import java.util.List;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface CallAnnotation {List<String> value() default {};}
public enum SourceEnum permits ExtendedSourceEnum {INSTANCE;
class MemberInner {class NestedInner {private TargetEnum targetParam;private int instanceField;
@CallAnnotation(value = TargetEnum::staticMethod)protected void recursiveMethod(TargetEnum target) {this.targetParam = target;instanceField++;
class LocalInner {<T extends CharSequence> void useBounds(T t) {System.out.println(t.length());}}new LocalInner().useBounds("boundsTest");
private class ConstructorHelper {ConstructorHelper() {super.toString();this.targetParam = SourceEnum.this.MemberInner.this.NestedInner.this.targetParam;}}new ConstructorHelper();
int i = 0;do {variableCall(targetParam);i++;} while (i < 1);
if (instanceField < 3) {recursiveMethod(target);}}
private void variableCall(TargetEnum target) {System.out.println(target.getInstanceField());}}}}
enum ExtendedSourceEnum implements SourceEnum {}
private enum TargetEnum {TARGET_INSTANCE;
private int instanceField;
TargetEnum() {class LocalInner {void init() {instanceField = 10;}}new LocalInner().init();}
public static List<String> staticMethod() {return List.of("targetStatic");}
int getInstanceField() {return instanceField;}}