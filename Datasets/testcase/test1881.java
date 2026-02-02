// Source package: testpackage test;
import com.other.OtherProcessor;import java.util.function.Function;
non-sealed class SourceClass<T> {// Type parameter usage in static nested classpublic static class SourceStaticNested {
U value;
}
// Member inner class with type parameterpublic class SourceInner {/**
Processes target inner record and returns TargetClass instance
@param targetRec Target inner record to process
@return Processed TargetClass instance*/public TargetClass<T> normalMethod(TargetClass<T>.InnerRec targetRec) {TargetClass<T> result = new TargetClass<>();
// Variable callresult.setValue(targetRec.data());
// Switch statementswitch (targetRec.data().toString().length() % 3) {case 0:result.addLog("Short");break;case 1:result.addLog("Medium");break;case 2:result.addLog("Long");break;}
// Functional interface with 3 varargs others_class methodsFunction<TargetClass<T>.InnerRec[], TargetClass<T>> processor = OtherProcessor::processAll;result = processor.apply(new TargetClass<T>.InnerRec[]{targetRec});
// Private WhileStatement with 3 super.field references (diff package)OtherProcessor.handleLoop(targetRec);
return result;}}}
// Target package: testpackage test;
import java.util.ArrayList;import java.util.List;
private class TargetClass<T> extends TargetParent {private T value;private List<String> logs = new ArrayList<>();
public TargetClass() {// Anonymous inner classRunnable initializer = new Runnable() {@Overridepublic void run() {value = null;}};initializer.run();}
public void setValue(T val) {this.value = val;}
public void addLog(String log) {logs.add(log);}
public record InnerRec(T data) {}}
// Parent class for TargetClassclass TargetParent {protected int superField1 = 10;protected int superField2 = 20;protected int superField3 = 30;}
// Other package: com.otherpackage com.other;
import test.TargetClass;import java.util.Arrays;
public class OtherProcessor {// Varargs method processing 3 elementspublic static <T> TargetClass<T> processAll(TargetClass<T>.InnerRec... recs) {TargetClass<T> target = new TargetClass<>();if (recs.length >= 1) target.setValue(recs[0].data());if (recs.length >= 2) target.addLog(recs[1].data().toString());if (recs.length >= 3) target.addLog(recs[2].data().toString());return target;}
// Private WhileStatement with 3 super.field referencespublic static <T> void handleLoop(TargetClass<T>.InnerRec rec) {class LoopProcessor {private void execute() {TargetClass<T> target = new TargetClass<>();int count = 0;while (count < 3) {switch (count) {case 0:System.out.println(target.superField1);break;case 1:System.out.println(target.superField2);break;case 2:System.out.println(target.superField3);break;}count++;}}}new LoopProcessor().execute();}}
