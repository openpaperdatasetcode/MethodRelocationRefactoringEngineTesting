package source;
import java.util.List;import target.TargetRecord;
record SourceRecord(String data) {public class InnerSource {/**
Varargs method to process TargetRecord instances
@param targets variable-length TargetRecord parameters
@return processed base type result
@throws Exception if processing fails*/public final int varargsMethod(TargetRecord<String>... targets) throws Exception {class LocalInner {protected List<String> abstractMethod(TargetRecord<String> target) {throw new UnsupportedOperationException("Abstract method implementation required");}}
LocalInner local = new LocalInner();int result = 0;
for (TargetRecord<String> target : targets) {if (target == null) {return -1;}
List<String> abstractResult = local.abstractMethod(target);result += abstractResult.size();
switch (TargetRecord.StaticNested.getStaticField()) {case 3:SubTargetRecord subTarget = new SubTargetRecord(target.value());result += subTarget.overrideMethod(target);break;default:break;}}
return result;}}}
package target;
import java.util.ArrayList;import java.util.List;
abstract record TargetRecord<T>(T value) {public static class StaticNested {public static final int STATIC_FIELD = 3;
public static int getStaticField() {return STATIC_FIELD;}}
public abstract int process(T value);}
class SubTargetRecord extends TargetRecord<String> {public SubTargetRecord(String value) {super(value);}
@Overrideprotected Object overrideMethod(TargetRecord<String> target) {return target.value().length() * TargetRecord.StaticNested.STATIC_FIELD;}
@Overridepublic int process(String value) {return value.length();}}