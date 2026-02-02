package samepkg;
import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
interface ProcessInterface {List<String> process(TargetEnum target);}
protected enum SourceEnum implements ProcessInterface {INSTANCE_A, INSTANCE_B;
private TargetEnum targetField; // Per condition: source contains target's field
public static class StaticNestedSource {public static Object staticMethod1 (TargetEnum target) {return target.name ();}
public static Object staticMethod2(TargetEnum target) {return target.ordinal();}
public static Object staticMethod3(TargetEnum target) {return target.toString();}}
@Overrideprotected List<String> process(TargetEnum target) {this.targetField = target;
Class<?> targetType = TargetEnum.class;
Supplier<List<String>> anonymousSupplier = new Supplier<>() {@Overridepublic List<String> get() {return new ArrayList<>();}};
// If statementList<String> result = new ArrayList<>();if (target != null) {StaticNestedHelper helper = new StaticNestedHelper (StaticNestedSource.staticMethod1 (target),StaticNestedSource.staticMethod2 (target),StaticNestedSource.staticMethod3 (target));
GenericHelper<String> genericHelper = new GenericHelper<>(SourceEnum.this.genericMethod(target));
// Variable callresult.add(helper.getData1().toString());result.add(helper.getData2().toString());result.add(genericHelper.getData().get(0));}
// Assert statementassert result.size() <= 3 : "Result size exceeds limit";
return result;}
public <T extends Enum<T>> List<String> genericMethod(T target) {List<String> data = new ArrayList<>();data.add(target.name());return data;}
private static class StaticNestedHelper {private final Object data1;private final Object data2;private final Object data3;
public StaticNestedHelper (Object data1, Object data2, Object data3) {this.data1 = data1;this.data2 = data2;this.data3 = data3;}
public Object getData1() {return data1;}
public Object getData2() {return data2;}}
private static class GenericHelper {
private final List data;
public GenericHelper(List data) {
this.data = data;
}
public List getData() {
return data;
}
}
public void setTargetField(TargetEnum targetField) {this.targetField = targetField;}}
private enum TargetEnum {TARGET_X, TARGET_Y, TARGET_Z;}