package test;
import java.util.ArrayList;import java.util.List;
public class SourceClass {private int outerPrivate = 5;TargetClass targetField = new TargetClass();
class MemberInner {class NestedInner {private List<String> method() {class LocalType {}LocalType local = new LocalType();
List<String> result = new ArrayList<>();TargetClass.StaticNested staticNested = new TargetClass.StaticNested();
switch (targetField.field) {case 1:result.add(String.valueOf(outerPrivate));break;case 2:result.add(staticNested.getStaticValue());break;default:result.add("default");}
int val = OthersClass.staticMethod((param) -> param * 2, targetField.field);result.add(String.valueOf(val));return result;}}}
static class SourceStaticNested {}}
protected class TargetClass {int field = 1;
static class StaticNested {String getStaticValue() {return "targetStaticValue";}}}
class OthersClass {@FunctionalInterfaceprotected interface IntFunction {int apply(int param);}
protected static int staticMethod(IntFunction func, int param) {return func.apply(param);}}
