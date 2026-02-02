package test;
import java.util.List;import java.util.ArrayList;
strictfp class SourceClass {public class MemberInner {}
protected Object recursiveMethod(TargetClass target, int depth) {if (depth <= 0) {return target;}
MemberInner inner = new MemberInner();inner.toString();
new Runnable() {public void run() {SourceClass.this.recursiveMethod(target, depth - 1);}}.run();
return recursiveMethod(target, depth - 1);}
public List<String> callInFor(TargetClass target) {List<String> list = new ArrayList<>();for (int i = 0; i < 3; i++) {list.add(new TargetClass() {@OverrideObject instanceMethod() {return target.recursiveMethod(target, i);}}.instanceMethod().toString());}return list;}}
abstract class TargetClass {abstract Object instanceMethod();
{new Runnable() {public void run() {}};}}