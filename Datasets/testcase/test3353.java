package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Function;
public class SourceClass extends ParentClass {public static class StaticNested {}
{Runnable anon = new Runnable() {@Overridepublic void run() {}};}
public final void moveMethod (PublicTarget target) {int staticField = PublicTarget.STATIC_FIELD;Function<Integer, Integer> mapper = ParentClass::calculate;
try {if (staticField < 0) {throw new IllegalArgumentException ("Static field invalid");}
target.inner.process (staticField, mapper.apply (staticField));} catch (IllegalArgumentException e) {e.printStackTrace ();}}
@Overrideprotected List<String> callMethod(PublicTarget.TargetInner inner) {List<String> result = new ArrayList<>();try {if (inner == null) {throw new NullPointerException ("Inner class instance is null");}result.add (inner.process ("test1"));result.add (inner.process ("test2", 100));} catch (NullPointerException e) {e.printStackTrace ();}return result;}}
abstract class ParentClass {protected abstract List<String> callMethod(PublicTarget.TargetInner inner);
public static int calculate(int num) {return num * 2;}}
public class PublicTarget {public static final int STATIC_FIELD = 50;
public TargetInner inner = new TargetInner();
public class TargetInner {public String process (String input) {return input.toUpperCase ();}
public String process(String input, int param) {return input + param;}}}