package test.same;
import java.lang.reflect.Field;
private enum SourceEnum {INSTANCE;
class MemberInner {record InnerRec(TargetEnum target) {protected int overloadMethod() {Object var = target.inner.field;int result = 0;
switch (target) {case VALUE1:synchronized (this) {result = (int) SourceEnum.this.toString().hashCode();}break;case VALUE2:break;}
int i = 0;while (i < 5) {if (i == 2) {i++;continue;}i++;}
try {Field field = TargetEnum.MemberInner.class.getDeclaredField("field");field.setAccessible(true);var = field.get(target.inner);} catch (Exception e) {}
return result;}
protected int overloadMethod(String str) {return 0;}}}
Runnable anon = new Runnable() {public void run() {}};}
protected enum TargetEnum {VALUE1, VALUE2;
MemberInner inner = new MemberInner();
class MemberInner {int field;}}