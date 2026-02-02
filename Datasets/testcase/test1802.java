package source;
import target.TargetClass;import java.util.function.Consumer;
protected class SourceClass<T> {private T outerPrivate;
public void setValue(int value, TargetClass<String> target) {private class LocalType {int val = target.superField + 1;}LocalType local = new LocalType();
if (value > 0) {target.overloadedMethod(value);}
outerPrivate = (T) target;
do {Consumer<Integer> consumer = (param) -> this.overriddenMethod(param);consumer.accept(value);} while (value-- > 0);}
public void overloadedMethod(String str) {}
public void overriddenMethod(int param) {}}
package target;
public class TargetClass<T> extends ParentClass {protected int superField = 5;
{new Consumer<T>() {public void accept(T t) {System.out.println(superField);}};}
public int overloadedMethod(int num) {return super.parentMethod() + num;}}
package target;
class ParentClass {int parentMethod() {return 10;}}
