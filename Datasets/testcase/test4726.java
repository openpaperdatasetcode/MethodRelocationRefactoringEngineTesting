package test;
import java.util.List;
enum SourceEnum {VALUE1, VALUE2;
protected Object targetField;
public class MemberInner {void innerMethod() {}}
protected Object recursiveMethod(int n, TargetEnum target) throws Exception {if (n <= 0) {return super.toString();}
new Runnable() {public void run() {MemberInner inner = new MemberInner();inner.innerMethod();}};
try {String result = SubClass.callOverloaded(target.inner, n);if (result == null) {throw new Exception("Null result");}} catch (Exception e) {throw e;}
for (int i = 0; i < n; i++) {if (i == target.field) {break;}}
return recursiveMethod(n - 1, target);}}
protected enum TargetEnum {TARGET1, TARGET2;
int field;TargetInner inner = new TargetInner();
class TargetInner {void process() {class LocalInner {String getValue() {return "local";}}LocalInner li = new LocalInner();li.getValue();}}}
class SubClass extends SourceEnum {protected static String callOverloaded(TargetEnum.TargetInner inner, int num) {return inner.toString();}
protected static String callOverloaded(TargetEnum.TargetInner inner, String str) {return str;}}