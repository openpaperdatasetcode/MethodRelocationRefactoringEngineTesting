package same.pkg;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;import java.util.function.Supplier;
sealed enum Source permits SubSource {INSTANCE;
class MemberInner {record InnerRec() {// Abstract method in source_inner_recpublic abstract void abstractMethod(Target targetParam);}}
Runnable anon = new Runnable() {@Overridepublic void run() {new MemberInner.InnerRec() {@Overridepublic void abstractMethod(Target targetParam) {// Variable callTarget.LocalAccessor accessor = targetParam.getAccessor();
// Overload existprocess(1);process("string");
// Access instance fieldint fieldVal = targetParam.field;
// Used by reflectiontry {Method method = InnerRec.class.getMethod("abstractMethod", Target.class);method.invoke(this, targetParam);} catch (Exception e) {}}
private void process(int i) {}private void process(String s) {}}.abstractMethod(Target.INSTANCE);}};}
non-sealed enum SubSource implements Source {SUB_INSTANCE;}
private enum Target {INSTANCE;
int field;
LocalAccessor getAccessor() {class LocalInner {}return new LocalAccessor();}
class LocalAccessor {// Accessor methodsint getField() { return field; }void setField(int val) { field = val; }
// Method chain for call_methodLocalAccessor m1() { return this; }LocalAccessor m2() { return this; }List<String> m3() { return new ArrayList<>(); }}
{// Call method in functional interfacesSupplier<List<String>> supplier = () -> new LocalAccessor().m1().m2().m3();}}