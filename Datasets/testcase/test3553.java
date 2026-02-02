package sourcepkg;
import targetpkg.TargetClass;
public class SourceClass<T extends Number & Comparable<T>> {class MemberInner {}
public void outerMethod() {class LocalInner {class InnerRec {static TargetClass moveMethod(TargetClass target) throws Exception {
new Object();
private U var1 = target.getValue();private int var2 = var1.intValue();
try {variableCall(target);} catch (Exception e) {throw e;}
return target;}
private static void variableCall(TargetClass target) {
target.doTask();
}
}
}
LocalInner.InnerRec.moveMethod(new TargetClass<>());
}
}
package targetpkg;
/**
Javadoc for TargetClass: Final generic target class for Move Method refactoring
@param <T> Type parameter with bounds*/final class TargetClass<T extends Number> {private T value;
public T getValue() {return value;}
public void doTask() {}
static <T extends Number> TargetClass<T> moveMethod(TargetClass<T> target) {return target;}}