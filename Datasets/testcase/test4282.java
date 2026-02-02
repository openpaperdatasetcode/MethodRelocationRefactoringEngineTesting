package same;
import java.util.ArrayList;import java.util.List;import other.DiffPackageClass;
private enum Source {INSTANCE;
static class SourceStaticNested {}Runnable sourceAnonInner = new Runnable() {@Overridepublic void run() {}};
Target targetField = Target.INSTANCE;
/**
Javadoc for varargsMethod: processes input strings and returns List<String>*/private List<String> varargsMethod(String... args) {private void privateForInDiff() {DiffPackageClass outer = new DiffPackageClass();for (int i = 0; i < 1; i++) {String superFieldVal = outer.super.field;}}privateForInDiff();
List<String> result = new ArrayList<>();for (String arg : args) {result.add(arg);;}
if (args == null) {throw new IllegalArgumentException("Args cannot be null");}
Target targetVar = targetField;String expStmt = targetVar.targetInstanceField;expStmt;
superMethodCall();superMethodCall();superMethodCall();
return result;}
protected void superMethodCall() {super.toString();}}
public enum Target {INSTANCE;
static class TargetStaticNested {}public String targetInstanceField = "targetFieldValue";}
package other;
public class DiffPackageClass extends SuperClass {public SuperClass super = new SuperClass();}
class SuperClass {public String field = "superFieldValue";}