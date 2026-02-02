package test;
interface TargetInterface {
String processData ();
}
class SourceClass {
public static class StaticNestedOne {public static String getPrefix () {return "prefix_one_";}}
public static class StaticNestedTwo {public static String getSuffix() {return "_suffix_two";}}
private Object varargsMethod(TargetClass<String>... targets) {
String outerPrefix = StaticNestedOne.getPrefix ();
String outerSuffix = StaticNestedTwo.getSuffix ();
for (TargetClass<String> target : targets) {
String targetData = target.data;String processed = outerPrefix + targetData + outerSuffix;
TargetClass.StaticNested targetNested = target.new StaticNested ();processed = targetNested.format (processed);
System.out.println ("Interface method result:" + target.processData ());}
return targets;}
@Overridepublic boolean equals (Object obj) {return super.equals (obj);}}
class TargetClass<T> implements TargetInterface {
T data;
public TargetClass(T data) {this.data = data;}
@Override
public String processData () {
return data.toString ().toUpperCase ();
}
public static class StaticNested {
public String format (String input) {
return input.trim () + "_formatted";
}
}
}