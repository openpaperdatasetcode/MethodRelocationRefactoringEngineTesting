package source;
import target.TargetRecord;import target.OthersClass;
record SourceRecord(String data) {public class InnerA {public class InnerRecursive {public int process(TargetRecord target) {int result = 0;int count = 0;
while (count < 2) {result += TargetRecord.STATIC_FIELD;result += target.value();count++;}
switch (target.value()) {case 1:result *= 2;break;case 2:result += OthersClass.getAccessor(target);break;default:result = 0;}
Runnable anon = new Runnable() {@Overridepublic void run() {result += target.value();}};anon.run();
result += new InnerA().calculate(target);return result;}}}
public class InnerA {public int calculate(TargetRecord target) {return target.value() * 3;}}}
package target;
public record TargetRecord(int value) {public static final int STATIC_FIELD = 10;
public TargetRecord {Runnable init = new Runnable() {@Overridepublic void run() {value = Math.max(value, 0);}};init.run();}}
package target;
class OthersClass {static int getAccessor(TargetRecord target) {return target.value() + TargetRecord.STATIC_FIELD;}}
