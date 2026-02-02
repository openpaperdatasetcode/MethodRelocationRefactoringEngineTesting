package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
interface SourceInterface {}
interface TargetInterface {}
public class SourceClass implements SourceInterface {public class InnerClass1 {}
public class InnerClass2 {}
List<String> methodToMove<T>(TargetClass target) {List<String> result = new ArrayList<>();
try {Method method = TargetClass.InnerRec.class.getMethod("getField");result.add(method.invoke(target.inner).toString());} catch (Exception e) {// No new exception}
result.add(String.valueOf(target.inner.targetField));result.add(SourceClass.this.toString());
return result;}}
class TargetClass implements TargetInterface {InnerRec inner = new InnerRec();
class InnerRec {String targetField = "test";
String getField() {return targetField;}}}