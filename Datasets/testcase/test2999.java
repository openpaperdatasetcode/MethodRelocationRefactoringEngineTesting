import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;
record SourceClass(String data) {static class StaticNested {Runnable runnable = new Runnable() {public void run() {TargetClass tc = new TargetClass("nested");tc.inner.overload();}};}
List<String> process(TargetClass... targets) throws Exception {super();List<String> result = new ArrayList<>();try {for (TargetClass target : targets) {result.add(target.inner.overload(1));do {String val = target.inner.overload("test");result.add(val);} while (false);}
Method method = TargetClass.Inner.class.getMethod("overload");method.invoke(targets[0].inner);} catch (ReflectiveOperationException e) {throw e;}return result;}}
record TargetClass(String info) {class Inner extends SuperType {protected String overload() {return "overload";}
protected String overload(int num) {return "overload:" + num;}
protected String overload(String str) {return super.overload(str);}}
Inner inner = new Inner();}
class SuperType {String overload(String str) {return "super:" + str;}}
