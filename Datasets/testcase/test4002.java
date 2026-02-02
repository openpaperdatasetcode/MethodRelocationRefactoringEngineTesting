package test;
import java.util.List;import java.util.ArrayList;import java.util.Collection;
public class TargetClass {private int targetPrivateField;
class TargetInner {List<String> innerList = new ArrayList<>();
List<String> recursiveInnerMethod(int depth) {if (depth <= 0) {return innerList;}innerList.add("depth-" + depth);return recursiveInnerMethod(depth - 1);}}
public void createAnonymous() {Runnable r = new Runnable() {public void run() {}};}}
public class SourceClass {private int outerPrivateField = 10;
static class SourceStaticNested {static int staticMethod(List<String> list) {return list.size();}}
public void createAnonymous() {Runnable r = new Runnable() {public void run() {}};}
private List<String> instanceMethod(String... keywords) {TargetClass target = new TargetClass();TargetClass.TargetInner targetInner = target.new TargetInner();List<String> result = new ArrayList<>();
int depth = keywords.length;do {result.addAll(targetInner.recursiveInnerMethod(depth));depth--;} while (depth > 0);
OthersClass others = new OthersClass();for (String kw : keywords) {kw.isEmpty() ? others.processSuper(kw) : others.processSuper(outerPrivateField + kw);}
Collection<String> coll = result;coll.removeIf(s -> SourceStaticNested.staticMethod(result) > 5);
return result;}
protected int getOuterPrivate() {return outerPrivateField;}}
class OthersClass extends ParentClass {public void processSuper(Object obj) {super.parentMethod(obj.toString());}}
class ParentClass {protected void parentMethod(String s) {}}
