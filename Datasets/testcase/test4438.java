package test;
enum SourceEnum<T> {VALUE1, VALUE2;
static class NestedA {}static class NestedB {int nestedField = 3;}
class Inner {int process(TargetEnum.InnerTarget param) {private int var = TargetEnum.Nested.staticField;int result = 0;int i = 0;
do {result += param.innerValue;i++;} while (i < 3);
try {NestedA nestedA = new NestedA();for (TargetEnum e : TargetEnum.values()) {result += e.ordinal();}} finally {}
return result;}
@Overridepublic String toString() {return "Custom toString";}}}
/**
Target enum with inner class*/public enum TargetEnum {TARGET1, TARGET2;
static class Nested {static int staticField = 3;}
class InnerTarget {int innerValue;}}