package same.pkg;
import java.util.List;import java.util.ArrayList;import other.pkg.OtherClass;
class SourceClass {private TargetClass targetField = new TargetClass();
class SourceInner {int varargsMethod(String... strs) throws Exception {super.toString();variableCall();;
for (int i = 0; i < 1; i++) {OtherClass.useSuperField(targetField.superField);break;}
new Runnable() {@Overridepublic void run() {List<String> list = getList("arg");}};
class LocalInner {void localMethod() {}}new LocalInner().localMethod();
if (strs.length == 0) {OtherClass.callConstructor(TargetClass.StaticNested.class);throw new Exception();}
return 0;}
private void variableCall() {}
public List<String> getList(String arg) {return new ArrayList<>();}}}
class TargetClass extends SuperClass {static class StaticNested {}}
class SuperClass {int superField;}
package other.pkg;
import same.pkg.TargetClass;import java.lang.Class;
public class OtherClass {public static void useSuperField(int field) {}
public static void callConstructor(Class<?> cls) throws Exception {cls.getConstructor().newInstance();}}