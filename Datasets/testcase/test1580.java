package source;
import target.TargetClass;import other.OthersClass;import java.lang.reflect.Method;
public class SourceClass {public static class StaticNested {public static int staticField = 0;}
private String name;
public void setName(String name) {this.name = name;}
public String getName() {return name;}
public Object process(TargetClass target) {class LocalHandler {void validate() {if (target == null) {throw new IllegalArgumentException("Target cannot be null");}}}LocalHandler handler = new LocalHandler();
try {// Accessor methods with this call in exception throwingthis.setName("source");if (this.getName() == null) {throw new IllegalStateException("Name not set");}
// Super keyword usageSystem.out.println(super.toString());
// Variable call - access target's fieldtarget.value = StaticNested.staticField++;
// Used by reflectionClass<?> cls = Class.forName("source.SourceClass");Method method = cls.getMethod("process", TargetClass.class);method.invoke(this, target);
// Call others class method in do-whileint count = 0;do {count = OthersClass.calculate(i -> i * 2, count);} while (count < 5);
return target.value;} catch (Exception e) {// no new exceptionreturn null;}}}
package target;
protected class TargetClass {public int value;}
package other;
import target.TargetClass;import java.util.function.Function;
public class OthersClass {protected static int calculate(Function<Integer, Integer> func, int param) {return func.apply(param) + 1;}}
