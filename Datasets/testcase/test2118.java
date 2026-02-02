package test;
import other.DiffPackageClass;import java.io.IOException;
interface TestInterface {}
private enum SourceEnum {INSTANCE;
void createLocalInner() {class LocalInner {}}
Runnable anonymous = new Runnable() {public void run() {}};
protected Object methodToMove(TargetEnum... targets) throws IOException {class LocalType {}LocalType local = new LocalType();
for (TargetEnum target : targets) {private int val1 = target.field;private String val2 = target.strField;
System.out.println(super.toString());target.variableCall();DiffPackageClass.process(target);}
return null;}}
strictfp enum TargetEnum implements TestInterface {VALUE1, VALUE2;
int field;String strField;
void variableCall() {Runnable r = new Runnable() {public void run() {}};}}
package other;
import test.TargetEnum;
public class DiffPackageClass {public static void process(TargetEnum target) {}}
