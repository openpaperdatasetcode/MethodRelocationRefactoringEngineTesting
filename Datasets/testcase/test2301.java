package same.pkg;
import java.util.List;import java.util.ArrayList;
protected class Source<T> {private T outerPrivateField;
class SourceInner {public List<String> varargsMethod(Target<Integer> target, String... params) {List<String> result = new ArrayList<>();result.add(target.innerField); // variable callresult.add(String.valueOf(outerPrivateField)); // access_outer_privateresult.add(target.new TargetInner().toString()); // depends_on_inner_classreturn result;}}
static class StaticNested {}
default int callMethod() {SourceInner inner = new SourceInner();Target<Integer> target = new Target<>();int val = 0;switch (val) {case 1:inner.varargsMethod(target, "a"); // instance callbreak;case 2:Runnable r = inner::varargsMethod; // method referencebreak;}return val;}}
protected class Target {
String innerField;
class TargetInner {// Override violation when moving varargsMethod here (no super method)public List<String> varargsMethod(Target<Integer> target, String... params) {return new ArrayList<>();}}}