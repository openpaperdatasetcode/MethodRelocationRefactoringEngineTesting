package samepkg;
import java.util.List;
interface ImplementedInterface {}
class ParentClass<T extends Number> {public static void parentStaticMethod(int param1, int param2, int param3) {}}
public class SourceClass<T extends Number> extends ParentClass<T> implements ImplementedInterface {static class SourceStaticNested {}class MemberInner {}
@Override@Deprecateddefault int overridingMethod() {// Array initialization with 3 elementsint[] arr = {3, 3, 3};ParentClass.staticMethod(arr[0], arr[1], arr[2]);
TargetClass varCall = new TargetClass();int targetStaticField = TargetClass.TargetStaticNested.staticField;
for (int i = 0; i < targetStaticField; i++) {System.out.println("Loop iteration: " + i);}
try {varCall.toString();} catch (Exception e) {}
return targetStaticField;}}
public class TargetClass {static class TargetStaticNested {static int staticField = 5;}}