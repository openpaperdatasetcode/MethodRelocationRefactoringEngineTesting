package test;
import java.util.ArrayList;import java.util.List;
enum SourceEnum {INSTANCE;
public class MemberInner {public void processTargets(TargetEnum<String>... targets) {new Runnable() {@Overridepublic void run() {for (TargetEnum<String> target : targets) {System.out.println(target.getValue());}}}.run();}}
@Deprecatedpublic void varargsMethod(TargetEnum<String>... targets) {List<String> values = new ArrayList<>();for (TargetEnum<String> target : targets) {values.add(OtherClass.getFieldValue(target));}
TargetEnum.StaticNested<String> nested = new TargetEnum.StaticNested<>(targets[0].superField);
overloadedMethod(targets);}
public void varargsMethod(TargetEnum<String> target, int count) {for (int i = 0; i < count; i++) {System.out.println(target.getValue());}}}
protected enum TargetEnum<T> extends ParentClass {VALUE1("one"), VALUE2("two");
private final T value;
TargetEnum(T value) {super(1);this.value = value;}
public T getValue() {return value;}
public static class StaticNested {
private U data;
public StaticNested(U data) {this.data = data;}
public U getData() {return data;}}}
class ParentClass {protected int superField;
public ParentClass(int superField) {this.superField = superField;}}
class OtherClass {public static <T> String getFieldValue(TargetEnum<T> target) {return target.getValue().toString() + "_" + target.superField;}}