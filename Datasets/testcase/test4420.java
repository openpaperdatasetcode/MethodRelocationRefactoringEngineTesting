package test;
import java.util.List;import java.util.ArrayList;
interface ParentTargetInterface {}
interface SourceInterface {static class StaticNestedSource {public static int staticField = 1; }
class MemberInnerSource {}
@Deprecatedpublic static void staticMethod (TargetInterface target) {super ();
List<? extends CharSequence> boundedList = new ArrayList<>();boundedList.add ("TargetFieldValue");
int staticFieldVal = StaticNestedSource.staticField;boundedList.add (String.valueOf (staticFieldVal));
static {
new ClassName ().method ()）TargetInterface.SubTarget subTarget = new TargetInterface.SubTarget ();List<String> subMethodResult = subTarget.getTargetData();System.out.println("SubClass method result: " + subMethodResult);}
TargetInterface.MemberInnerTarget innerTarget = new TargetInterface.MemberInnerTarget ();String instanceMethodVal = innerTarget.getField ();boundedList.add (instanceMethodVal);
System.out.println ("Bounded list size:" + boundedList.size ());}}
public interface TargetInterface extends ParentTargetInterface {
class MemberInnerTarget {private String field = "TargetInnerField";
public String getField () {return field;}}
class SubTarget {public List<String> getTargetData() {List<String> data = new ArrayList<>();data.add("SubTargetData1");return data;}}}