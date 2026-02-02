package test;
import java.lang.reflect.Method;import java.util.List;
enum SourceEnum {INSTANCE;
private TargetEnum targetField = TargetEnum.VALUE;
static class StaticNested<T extends CharSequence> {T processGenericData(T data) {return data;}}
class InnerClass {<T extends String> String genericMethod(T input, int depth) {if (depth <= 0) {return input;}
class LocalInner {String chainCall() {return INSTANCE.getTargetInner().getInnerData().trim();}}LocalInner local = new LocalInner();String var = local.chainCall();
String[] arr = {var, input};int i = 0;while (i < arr.length) {try {Method getMethod = TargetEnum.MemberInner.class.getMethod("getInnerData");String reflected = (String) getMethod.invoke(INSTANCE.getTargetInner());arr[i] += reflected;} catch (Exception e) {e.printStackTrace();}i++;}
return genericMethod(arr[0], depth - 1);}}
private TargetEnum.MemberInner getTargetInner() {return targetField.new MemberInner();}}
public enum TargetEnum {VALUE;
class MemberInner {public String getInnerData() {return "target_inner_data";}}}
class OthersClass {public String callMethod(String data) {SourceEnum.InnerClass inner = SourceEnum.INSTANCE.new InnerClass();String result;do {result = inner.genericMethod(data, 2);result = callMethod((str) -> str.toUpperCase(), result);} while (result.length() < 20);return result;}
public String callMethod(Converter converter, String data) {return converter.convert(data);}
@FunctionalInterfaceinterface Converter {String convert(String str);}}