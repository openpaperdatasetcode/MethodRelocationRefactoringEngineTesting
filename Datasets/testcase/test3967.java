package test;
import java.util.function.Function;
public enum SourceEnum {ENUM_INSTANCE;
class SourceInnerFirst {private Object innerField;}
class SourceInnerSecond {synchronized Object varargsMethod(TargetEnum.TargetStaticNested... targetNestedArray) {this.innerField = targetNestedArray;
TargetEnum.TargetStaticNested first = new TargetEnum.TargetStaticNested();TargetEnum.TargetStaticNested second = new TargetEnum.TargetStaticNested();TargetEnum.TargetStaticNested third = new TargetEnum.TargetStaticNested();
Function<String, Integer> ref1 = String::length;Function<Integer, String> ref2 = String::valueOf;Function<Double, String> ref3 = Double::toString;
Object varCall1 = first.getTargetField();Object varCall2 = second.getTargetField();Object varCall3 = third.getTargetField();
return new Object[]{varCall1, varCall2, varCall3};}
private Object innerField;}}
public enum TargetEnum<T> {TARGET_INSTANCE;
static class TargetStaticNested {private String targetField = "targetFieldValue";
public String getTargetField() {return targetField;}}}