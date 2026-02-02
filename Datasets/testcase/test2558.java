package same;
import java.lang.reflect.Method;
protected record SourceRecord(String data) {class MemberInner {public void process(TargetRecord target) {// Anonymous inner classRunnable anon = new Runnable() {@Overridepublic void run() {System.out.println("Processing: " + target.data());}};anon.run();
// VariableDeclarationStatement with target static fieldprivate int count = TargetRecord.STATIC_FIELD;
// Constructor invocation of target's inner classTargetRecord.InnerRec inner = target.new InnerRec();
// Do-while with accessor chainint i = 0;do {int value = inner.getValue().getNumber().intValue();i++;} while (i < count);
// Access instance fieldif (inner.detail == null) {throw new NullPointerException("Detail is null");}
// Used by reflectiontry {Method method = TargetRecord.InnerRec.class.getMethod("setValue", int.class);method.invoke(inner, 100);} catch (Exception e) {e.printStackTrace();}}}}
package same;
record TargetRecord(String data) {static final int STATIC_FIELD = 3;
class InnerRec {String detail = "inner detail";private int value = 0;
ValueWrapper getValue() {return new ValueWrapper(value);}
void setValue(int val) {this.value = val;}
class ValueWrapper {private final int number;
ValueWrapper(int number) {this.number = number;}
Integer getNumber() {return number;}}}}