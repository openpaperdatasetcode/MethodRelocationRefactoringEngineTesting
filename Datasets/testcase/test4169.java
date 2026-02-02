package source;
import target.TargetInterface;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
interface SourceInterface {class SourceMemberInner {private TargetInterface.TargetInnerRec targetRec;
public SourceMemberInner(TargetInterface.TargetInnerRec rec) {this.targetRec = rec;}}
static class SourceStaticNested {private static Object processRec(TargetInterface.TargetInnerRec rec) {return rec.getData();}}
private default Object normalMethod(TargetInterface.TargetInnerRec rec) {if (rec == null) throw new NullPointerException("rec is null");
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {String value() default SourceStaticNested.processRec(new TargetInterface.TargetInnerRec("annoVal")) + "";}
TargetInterface.TargetInnerRec varCall = rec;Object result = varCall.getData();
SourceMemberInner inner = new SourceMemberInner(varCall);int[] counts = {0};do {counts[0]++;} while (counts[0] < 3);
var switchResult = switch (counts[0]) {default -> 1;};
OthersClass others = new OthersClass();Object call1 = others.instanceMethod(varCall);Object call2 = others.instanceMethod(inner.targetRec);Object call3 = others.instanceMethod(new TargetInterface.TargetInnerRec("newData"));
return new ResultWrapper(varCall::getIntData,result,switchResult);}
record ResultWrapper(TargetInterface.TargetInnerRec.IntSupplier supplier, Object data, int count) {}}
package target;
protected interface TargetInterface {record TargetInnerRec(String data) {public Object getData() {class LocalInner {String process() {return data.toUpperCase();}}return new LocalInner().process();}
public int getIntData() {return data.length();}
@FunctionalInterfaceinterface IntSupplier {int getAsInt();}}}
package source;
class OthersClass {public Object instanceMethod(TargetInterface.TargetInnerRec rec) {return rec.getData();}}
