package same;
import other.DiffPackageOthers;
record Source(Target targetField) {class SourceMemberInner {public int processTarget(Target target) {return target.value().length();}}
Runnable sourceAnonInner = new Runnable() {@Overridepublic void run() {new SourceMemberInner().processTarget(targetField);}};
default int varargsMethod(String... params) {private void privateConstructorInDiff() {DiffPackageOthers diffObj = new DiffPackageOthers(DiffPackageOthers.STATIC_FIELD);}privateConstructorInDiff();
try {abstract String declaredVar;declaredVar = params.length > 0 ? params[0] : "default";} catch (Exception e) {return -1;}
Object var = targetField;int targetValLength = targetField.value().length();
return targetValLength + params.length;}
private int callTargetMethod(boolean useTarget) {if (useTarget) {return Target.staticMethod(targetField.value());} else {return targetField.value().hashCode();}}}
public record Target(String value) {public static int staticMethod(String param) {return param.hashCode();}}
package other;
public class DiffPackageOthers {public static final String STATIC_FIELD = "diff_static_val";
public DiffPackageOthers(String field) {// Constructor logic}}