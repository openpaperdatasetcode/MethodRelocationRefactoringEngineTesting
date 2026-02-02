package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.Collection;
class SourceClass {private TargetClass<String> targetField = new TargetClass<>("initData");
class FirstMemberInner {}
class SecondMemberInner {record SourceInnerRec(TargetClass<String> target) {}
private void instanceMethod (SourceInnerRec rec) {
try {Method reflectMethod = SecondMemberInner.class.getDeclaredMethod ("instanceMethod", SourceInnerRec.class);reflectMethod.setAccessible (true);reflectMethod.invoke (this, rec);} catch (Exception e) {e.printStackTrace ();}
TargetClass<String> varCall = rec.target();for (int i = 0; i < 1; i++) {int baseResult = varCall.callParentMethod();System.out.println(baseResult);}
Collection<String> dataList = new ArrayList<>();dataList.add(varCall.getData());TargetClass<String> result = OthersClass.processCollection(dataList,str -> new TargetClass<>(str.toUpperCase()));}
private void instanceMethod (TargetClass<String> directTarget) {System.out.println(directTarget.getData());}}}
public class TargetClass<T> extends ParentClass {private T data;
public TargetClass(T data) {this.data = data;}
public T getData() {return data;}
static class TargetStaticNested {static <T> void printData(TargetClass<T> target) {System.out.println(target.getData());}}}
class ParentClass {public int callParentMethod() {return 100;}}
class OthersClass {static <T> TargetClass<T> processCollection(Collection<T> coll, java.util.function.Function<T, TargetClass<T>> mapper) {return mapper.apply(coll.iterator().next());}
static <T> TargetClass<T> processCollection(T singleData, java.util.function.Function<T, TargetClass<T>> mapper) {return mapper.apply(singleData);}}
