package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Function;
private class SourceClass {protected String protectedField = "protectedValue";private TargetClass targetField = new TargetClass();
static class NestedRecord {List<String> innerMethod() {class LocalInner {}List<String> list = new ArrayList<>();
for (int i = 0; i < 3; i++) {list.add(SourceClass.protectedField);}
TargetClass target = new TargetClass();String lit1 = "literal1";String lit2 = "literal2";String lit3 = "literal3";
do {Function<String, Object> func = target::finalInstanceMethod;list.add((String) func.apply(lit1));} while (list.size() < 5);
targetField.doAction();this.instanceMethod();
return list;}
private void instanceMethod() {}}}
class TargetClass {public static String staticField1 = "field1";public static String staticField2 = "field2";public static String staticField3 = "field3";
void doAction() {class LocalInner {}}
public final Object finalInstanceMethod(String input) {return input.toUpperCase();}}