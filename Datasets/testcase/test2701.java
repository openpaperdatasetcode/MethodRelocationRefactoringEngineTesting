package test;
import java.io.IOException;import java.lang.reflect.Method;import java.util.function.Function;
public record SourceRecord(String data) {// Static nested class (source_feature)public static class SourceStaticNested {}
{// Anonymous inner class (source_feature)new Runnable() {@Overridepublic void run() {}};}
@Overridepublic TargetRecord<String> methodToMove(TargetRecord<String> target) {super(); // Super keywords
// MethodReference (numbers:2, modifier:public)Function<TargetRecord<String>, String> ref1 = TargetRecord::data;Function<TargetRecord<String>, String> ref2 = target::processData;
// For loop with others_class instance method callfor (int i = 0; i < 1; i++) {OthersClass others = new OthersClass();target = others.instanceMethod(target);}
// Variable call + contains target parameter (per_condition)target.toString();String targetField = target.data();
// IOException handling (no_new_exception)try {if (targetField.isEmpty()) throw new IOException("Empty data");} catch (IOException e) {// No rethrow}
// Used by reflectiontry {Method method = getClass().getMethod("methodToMove", TargetRecord.class);method.invoke(this, target);} catch (Exception e) {}
return target;}}
record TargetRecord<T>(T data) {public T processData() {return data;}}
class OthersClass {// Others_class instance methodprivate <T> TargetRecord<T> instanceMethod(TargetRecord<T> target) {return target;}}
// Parent class for override violation (record implicitly extends Record)abstract class ParentRecord {public TargetRecord<String> methodToMove(TargetRecord<String> target) {return target;}}