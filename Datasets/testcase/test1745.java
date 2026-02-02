package test;
import java.util.ArrayList;import java.util.List;
class BaseEnum {protected String protectedField = "base_protected";
protected List<String> baseMethod() {return new ArrayList<>();}}
public enum SourceEnum<T extends CharSequence> extends BaseEnum {VALUE1, VALUE2;
class MemberInner {private List<String> process(TargetEnum target, String... args) {labeled: {SourceEnum.StaticNested nested = new SourceEnum.StaticNested();nested.id = 10;
List<String> result = super.baseMethod();String var1 = target.inner.getValue();String var2 = protectedField;
for (String arg : args) {if (arg.isEmpty()) break labeled;result.add(var1 + var2 + arg);}
target.inner.setValue(var1 + var2);}
return new ArrayList<>();}}
static class StaticNested {int id;}}
/**
Target enum with member inner class*/protected enum TargetEnum {DEFAULT, CUSTOM;
MemberInner inner = new MemberInner();
class MemberInner {private String value;
String getValue() {return value;}
void setValue(String val) {this.value = val;}}}