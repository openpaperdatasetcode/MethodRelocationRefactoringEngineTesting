package test;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;
interface MyInterface {String getValue();}
public enum SourceEnum extends ParentClass {INSTANCE;
public class MemberInner {public class NestedInner {synchronized List<String> process(TargetEnum... targets) throws Exception {class LocalInner {String getLocalData() {return "local";}}
LocalInner local = new LocalInner();List<String> result = new ArrayList<>();result.add(local.getLocalData());
try {for (TargetEnum target : targets) {result.add(target.data);result.add(TargetEnum.StaticNested.getValue(target));}
Method method = TargetEnum.class.getMethod("getValue");result.add((String) method.invoke(TargetEnum.FIRST));} catch (ReflectiveOperationException e) {throw e;}
switch (targets.length) {case 0:return result;default:return OthersClass.concat(result, "processed");}}}}}
class ParentClass {}
class OthersClass {public static List<String> concat(List<String> list, String... suffixes) {for (String s : suffixes) {list.add(s);}return list;}}
public enum TargetEnum implements MyInterface {FIRST("first"), SECOND("second");
public final String data;public static class StaticNested {public static String getValue(TargetEnum e) {return e.data.toUpperCase();}}
TargetEnum(String data) {this.data = data;}
@Overridepublic String getValue() {return data;}}