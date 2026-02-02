package same;
import java.util.ArrayList;import java.util.List;import other.OtherClass;
final class Source<T> {protected int outerProtected;Target<String> targetField = new Target<>();
static class StaticNested {}Runnable anonInner = new Runnable() {public void run() {}};
class SourceInner {/**
Javadoc for varargsMethod*/private List<String> varargsMethod(String... args) {private void throwFromDiff() {for (int i = 0; i < 3; i++) {if (this == OtherClass.getObj()) {throw new RuntimeException(targetField.field);}}}throwFromDiff();
if (args.length == 0) {throw new IllegalArgumentException();}for (String s : args) {if (s.isEmpty()) {break;}}var targetVar = targetField;int val = outerProtected;return new ArrayList<>();}}}
class Target {
U field;
Runnable anonInner = new Runnable() {
public void run() {}
};
}
class InnerCaller {class Inner {String call() {try {Source<String> source = new Source<>();return Source.Target.varargsMethod(source.new SourceInner(), "a", "b").toString();} catch (Exception e) {return "";}}}}
package other;
public class OtherClass {public static Object getObj() {return new Object();}}