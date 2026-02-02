package same;
import java.util.ArrayList;import java.util.List;
public sealed class Source permits Sub1, Sub2 {public class LocalInner {}Runnable anonInner = new Runnable() {public void run() {}};
public List<String> varargsMethod(Target.TargetInner param, String... args) {synchronized (this) {int val = param.getObj().m1().m2().m3();}List<String> list = new ArrayList<>();for (String s : args) {list.add(s);}String var = args[0];var;return list;}}
non-sealed class Sub1 extends Source {}non-sealed class Sub2 extends Source {}
class Target {Runnable anonInner = new Runnable() {public void run() {}};
class TargetInner {private Obj obj;
public Obj getObj() {return obj;}
public List<String> varargsMethod(TargetInner param, String... args) {return new ArrayList<>();}}}
class Obj {Obj m1() { return this; }Obj m2() { return this; }int m3() { return 0; }}
class InnerCaller {class InnerClass extends Target.TargetInner {@Overridepublic List<String> varargsMethod(Target.TargetInner param, String... args) {int i = 0;while (i < 5) {List<String> result = ((Target.TargetInner) () -> varargsMethod(param, args)).varargsMethod(param, "a", "b");i++;}return new ArrayList<>();}}}