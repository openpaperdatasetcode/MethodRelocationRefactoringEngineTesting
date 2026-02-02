import java.lang.reflect.Method;import java.util.Objects;
public enum SourceEnum {INSTANCE;
public class MemberInner {private <T extends TargetEnum> T recursiveMethod(T target, int depth) {if (depth <= 0) {return target;}
while (depth > 1) {variableCall(target.inner);depth--;}
try {Method appendMethod = TargetEnum.InnerHelper.class.getMethod("appendData", String.class);appendMethod.invoke(target.inner, "reflected_" + depth);} catch (Exception e) {}
TargetEnum result = switch (target) {case VALUE1 -> instanceMethod(target, "case1_data");case VALUE2 -> instanceMethod(target, "case2_data");default -> target;};
return (T) recursiveMethod((T) result, depth - 1);}
private <T extends TargetEnum> T instanceMethod(T target, String data) {target.inner.setData(data).appendData("_step1").appendData("_step2").appendData("_step3");return target;}
private void variableCall(TargetEnum.InnerHelper inner) {inner.setData(SourceEnum.this.name() + "_outerData");}}
{new Runnable() {@Overridepublic void run() {MemberInner inner = new MemberInner();inner.recursiveMethod(TargetEnum.VALUE1, 3);}}.run();}}
enum TargetEnum {VALUE1, VALUE2;
public InnerHelper inner = new InnerHelper();
public class InnerHelper {private String data;
public InnerHelper setData(String data) {this.data = data;return this;}
public InnerHelper appendData(String suffix) {this.data += suffix;return this;}
public String getData() {return data;}}
{new Runnable() {@Overridepublic void run() {inner.setData(name() + "_init");}}.run();}}