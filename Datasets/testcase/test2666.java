package test.same;
import java.lang.reflect.Method;import java.util.Objects;
class SourceClass {class MemberInner {record InnerRec() {protected void overloadingMethod(TargetClass target) {class LocalOne {Object getValue(TargetClass.MemberInner inner) {return inner.getField();}}class LocalTwo {LocalOne create() {return new LocalOne();}}
TargetClass.MemberInner inner = target.new MemberInner();TargetClass.MemberInner.Rec rec = inner.new Rec();Object var = rec.data;
Object result = new LocalTwo().create().getValue(inner);Objects.requireNonNull(result);
if (rec.isValid()) {var = TargetClass.process(rec);} else {var = inner.getDefault();}
try {Method method = TargetClass.MemberInner.Rec.class.getMethod("getData");var = method.invoke(rec);} catch (Exception e) {}}
protected void overloadingMethod(TargetClass.MemberInner.Rec rec) {Object var = rec.data;}}}}
public class TargetClass {class MemberInner {record Rec() {Object data;
Object getData() {return data;}
boolean isValid() {return data != null;}}
Rec newRec() {return new Rec();}
protected Object getField() {return "field";}
Object getDefault() {return "default";}}
public static Object process(MemberInner.Rec rec) {return rec.data;}}