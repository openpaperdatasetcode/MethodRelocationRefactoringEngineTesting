package test;
import java.io.IOException;import java.lang.reflect.Method;
protected record SourceRecord(String data) {// Member inner classpublic class SourceInner {/**
Overloading method to process TargetRecord inner class
@param target TargetRecord instance
@return Processed integer value
*/
protected int methodToMove(TargetRecord target) {
return process(target, 1);
}
/**
Overloading method with multiplier
@param target TargetRecord instance
@param multiplier Value multiplier
@return Processed integer value
*/
protected int methodToMove(TargetRecord target, int multiplier) {
return process(target, multiplier);
}
private int process(TargetRecord target, int multiplier) {super(); // Super constructor invocationint result = 0;TargetRecord.TargetInner inner = target.new TargetInner();
// Variable call + contains target field (per_condition)String targetField = inner.field;
// This.var = varthis.resultHolder = result;
// Expression statementresult = targetField.length() * multiplier;
// 2 ClassInstanceCreation expressions (default modifier)default TargetRecord.TargetInner inner1 = new TargetRecord.TargetInner();default TargetRecord.TargetInner inner2 = new TargetRecord.TargetInner();
// Try statement with IOExceptiontry {if (targetField.isEmpty()) {throw new IOException("Empty field");}result += inner1.field.length() + inner2.field.length();} catch (IOException e) {// No new exceptionresult = 0;}
// Used by reflectiontry {Method method = TargetRecord.TargetInner.class.getMethod("getFieldLength");result += (int) method.invoke(inner);} catch (Exception e) {// No new exception}
// Anonymous inner classRunnable anon = new Runnable() {@Overridepublic void run() {System.out.println("Processed: " + result);}};anon.run();
return result;}
private int resultHolder;}}
public record TargetRecord() {public class TargetInner {public String field = "targetField"; // Source contains target's field
public int getFieldLength() {return field.length();}}}