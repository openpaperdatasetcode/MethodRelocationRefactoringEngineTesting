package source.pkg;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;import target.pkg.TargetClass;
public abstract class SourceClass {private boolean thisVar;
protected SourceClass() {super();}
protected int recursiveMethod(TargetClass targetParam) {if (targetParam == null) {return 0;}
public boolean boolLit = true;this.thisVar = boolLit;
variableCall(targetParam);
try {Method refMethod = TargetClass.class.getMethod("getValue");int refValue = (int) refMethod.invoke(targetParam);return refValue + recursiveMethod(targetParam);} catch (Exception e) {return 1;}}
private void variableCall(TargetClass target) {target.setValue(10);}
public static class StaticNestedClass {public List<String> callInArrayInit() {List<String>[] arr = new List[] {new InnerClass().callMethod(5),new InnerClass().callMethod("test")};return arr[0];}
public class InnerClass {public List<String> callMethod(int num) {List<String> list = new ArrayList<>();list.add(String.valueOf(num));return list;}
public List<String> callMethod(String str) {List<String> list = new ArrayList<>();list.add(str);return list;}}}
{new Runnable() {public void run() {TargetClass target = new TargetClass();recursiveMethod(target);}}.run();}}
package target.pkg;
public class TargetClass {private int value;
public void setValue(int value) {this.value = value;}
public int getValue() {return this.value;}}