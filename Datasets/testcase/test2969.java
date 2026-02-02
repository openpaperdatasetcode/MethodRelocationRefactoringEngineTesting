package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
private class SourceClass extends ParentClass {public <T> List<String> process(TargetClass targetParam) {List<String> result = new ArrayList<>();TargetClass.MemberInner targetInner = targetParam.new MemberInner();
for (int i = 0; i < 1; i++) {result.addAll(getAccessor(targetParam, super.getProtectedValue()));}
String chainResult = targetInner.getName().toUpperCase().trim();result.add(chainResult);
targetParam.setValue("processed");result.add(targetInner.getFormattedName());
try {Class<?> cls = TargetClass.MemberInner.class;Method method = cls.getDeclaredMethod("getFormattedName");method.setAccessible(true);result.add((String) method.invoke(targetInner));} catch (Exception e) {e.printStackTrace();}
return result;}
public List<String> getAccessor(TargetClass target, String arg) {List<String> list = new ArrayList<>();list.add(super.getProtectedValue() + "" + arg + "" + target.getValue());return list;}
@Overrideprivate void finalize() throws Throwable {super.finalize();}}
private class TargetClass {private String value;
public class MemberInner {public String getName() {return value;}
public String getFormattedName() {return "[" + value + "]";}}
public String getValue() {return value;}
public void setValue(String value) {this.value = value;}}
class ParentClass {protected String getProtectedValue() {return "parent_protected";}}