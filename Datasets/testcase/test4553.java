package com.source;
import com.target.TargetClass;import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;
public class SourceClass<T> implements MyInterface {private TargetClass targetField = new TargetClass();
class MemberInner {class SourceInnerRec {final List<String> method() {variableCall();List rawList = new ArrayList(); // Raw type
String expr = targetField.publicField + "_expr";
if (targetField != null) {Supplier<Integer> supplier = () -> InnerClass.callTargetStaticMethod();rawList.add(supplier.get());}
class LocalInner {void localMethod() {}}new LocalInner().localMethod();
return (List<String>) rawList;}
private void variableCall() {String localVar = targetField.publicField;}}}
static class InnerClass {protected static int callTargetStaticMethod() {return TargetClass.StaticNested.staticMethod();}}}
interface MyInterface {}
package com.target;
private class TargetClass {public String publicField = "targetField";
static class StaticNested {static int staticMethod() {return 42;}}}