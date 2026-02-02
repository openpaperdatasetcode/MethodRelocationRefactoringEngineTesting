package source;
import target.TargetClass;import others.OthersClass;import java.util.List;
class BaseSourceClass {}
protected class SourceClass extends BaseSourceClass {public class FirstInner {public class InnerRec {private TargetClass targetField;
private void instanceMethod() {targetField = new TargetClass();super.getClass();
<T extends List & CharSequence> void boundedMethod(T input) {}boundedMethod((T) List.of("bounded"));
for (int i = 0; i < 5; i++) {if (i == targetField.targetIntField) {break;}System.out.println(targetField.targetStrField);}
int type = 1;switch (type) {case 1:Object result1 = new SourceClass().new FirstInner().new InnerRec().callOthersMethod(targetField);break;case 2:Object result2 = new SourceClass().new SecondInner().new InnerRec().callOthersMethod(targetField);break;}}
private Object callOthersMethod(TargetClass target) {return OthersClass.genericMethod(target);}}}
public class SecondInner {public class InnerRec {private Object callOthersMethod(TargetClass target) {return OthersClass.genericMethod(target);}}}}
package target;
protected class TargetClass {public int targetIntField = 2;public String targetStrField = "target-field";
public TargetClass() {new Runnable() {@Overridepublic void run() {System.out.println(targetIntField);}}.run();}}
package others;
import target.TargetClass;
public class OthersClass {public static <T extends TargetClass> Object genericMethod(T target) {return target.targetStrField;}
public static <T extends TargetClass> Object genericMethod(T target, int num) {return target.targetIntField + num;}}