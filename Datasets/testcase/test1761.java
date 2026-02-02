package test;
import java.util.ArrayList;import java.util.List;
private enum SourceEnum {INSTANCE;
public List<String> process(TargetEnum target, String... args) {List<String> result = new ArrayList<>();TargetEnum.InnerClass inner = target.new InnerClass();
switch (target) {case VALUE1:inner.setValue("value1");break;case VALUE2:inner.setValue("value2");break;default:inner.setValue("default");}
for (String arg : args) {result.add(inner.getValue() + arg);}
return result;}
public List<String> process(Integer... nums) {List<String> result = new ArrayList<>();for (int num : nums) {result.add(String.valueOf(num));}return result;}}
protected enum TargetEnum {VALUE1, VALUE2, DEFAULT;
class InnerClass {private String value;
void setValue(String val) {this;
void setValue(String val) {this.value = val;}
String getValue() {return value;}}}