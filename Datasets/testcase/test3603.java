package test;
import java.util.ArrayList;import java.util.List;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnot {}
abstract class SourceClass {class InnerFirst {class InnerSecond {@MyAnnotpublic List<String> instanceMethod(TargetClass target) {List rawList = new ArrayList();rawList.add("item");List<String> result = (List<String>) rawList;
int count = 0;while (true) {count++;if (count > 3) break;result.add(String.valueOf(count));}
String var = target.nestedStatic.getValue();result.add(var);variableCall(var);
try {throw new Exception(OthersClass.newInstance().Inner.method());} catch (Exception e) {result.add(e.getMessage());}
return result;}
private void variableCall(String s) {result.add(s.toUpperCase());}}}
class AnotherInner {}}
/**
Javadoc for TargetClass
*/
protected class TargetClass {
static class NestedStatic {
String getValue() {
return "nested";
}
}
NestedStatic nestedStatic = new NestedStatic();
}
class OthersClass {static OthersClass newInstance() {return new OthersClass();}
class Inner {int method() {return 42;}}Inner Inner = new Inner();}