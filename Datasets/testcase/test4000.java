package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;import java.io.IOException;
class TargetClass {protected int targetProtectedField = 5;
class TargetInner {String innerData;
void innerMethod() {}}}
private class SourceClass extends ParentClass {private TargetClass.TargetInner targetInnerField = new TargetClass().new TargetInner();
static class SourceStaticNested {}
void createLocalInner() {class SourceLocalInner {}}
class SourceInner {@Overrideprivate void overridingMethod() throws IOException {if (targetInnerField == null) {throw new IOException("Target inner is null");}
List<String> dataList;if (targetInnerField.innerData != null) {OthersClass others = new OthersClass();dataList = others.varargsMethod(1, targetInnerField.innerData, "extra");} else {dataList = new ArrayList<>();};
for (String item : dataList) {targetInnerField.innerData = item;}
try {Method method = TargetClass.TargetInner.class.getMethod("innerMethod");method.invoke(targetInnerField);} catch (Exception e) {}
String result;do {result = new SourceClass().new SourceInner().callInstanceMethod();} while (result == null);}
public String callInstanceMethod() {return targetInnerField.innerData;}}}
class ParentClass {protected void overridingMethod() throws IOException {}}
class OthersClass {protected List<String> varargsMethod(int param, String... args) {List<String> list = new ArrayList<>();for (String arg : args) {list.add(arg);}return list;}}