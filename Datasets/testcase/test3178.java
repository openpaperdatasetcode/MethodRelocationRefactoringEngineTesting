import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
class ParentSource {}
public class SourceClass extends ParentSource {static class StaticNested {}
class MemberInner {record InnerRecord<T>(TargetClass<T> target) {strictfp TargetClass<T> process() {// Raw type usageList rawList = new ArrayList();rawList.add(target);
// Constructor invocation + 3 static method calls (constructor parameter list pos)OtherClass helper = new OtherClass(OtherClass.staticMethod1(target, "param1"),OtherClass.staticMethod2(target, 100),OtherClass.staticMethod3(target, rawList));
// DoStatement with 3 this.field references (diff_package_target pos)int count = 0;do {target.innerField = "value_" + count;assert target.innerField != null : "Field cannot be null";target.new TargetInner<T>().setValue(target.innerField);count++;} while (count < 3);
// Expression statement + variable callString data = target.new TargetInner<T>().getValue();rawList.add(data);
// Reflection usagetry {Method innerMethod = TargetClass.TargetInner.class.getDeclaredMethod("getValue");innerMethod.setAccessible(true);rawList.add(innerMethod.invoke(target.new TargetInner<T>()));} catch (Exception e) {}
// Depend on inner classtarget.new TargetInner<T>().processData(rawList);
return target;}}}
// Static code block: call others_class overloading methodsstatic {TargetClass<String> staticTarget = new TargetClass<>();OtherClass.finalOverload(staticTarget, "static1");OtherClass.finalOverload(staticTarget, 200);OtherClass.finalOverload(staticTarget, new ArrayList<>());}}
protected class TargetClass<T> {public String innerField;
class TargetInner<T> {private T value;
void setValue(T val) {this.value = val;}
T getValue() {return value;}
void processData(List<?> data) {data.forEach(System.out::println);}}}
class OtherClass {public OtherClass(Object... args) {}
// 3 static methods for constructor parameter listpublic static Object staticMethod1(TargetClass<?> target, String arg) {return target.innerField + arg;}
public static Object staticMethod2(TargetClass<?> target, int arg) {return target.innerField.length() + arg;}
public static Object staticMethod3(TargetClass target, List arg) {return target.innerField + arg.size();}
// Final overloading methods (static code block pos)public static final int finalOverload(TargetClass<?> target, String arg) {return arg.length();}
public static final int finalOverload(TargetClass<?> target, int arg) {return arg * 2;}
public static final int finalOverload(TargetClass target, List arg) {return arg.size();}}