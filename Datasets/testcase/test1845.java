package test;
import java.util.function.IntConsumer;
public enum SourceEnum {INSTANCE;
// Member inner classpublic class SourceMemberInner {public record SourceInnerRec() implements Processable {/**
Overrides process method to handle TargetEnum instances
@param target TargetEnum instance to process
@return Processed TargetEnum instance*/@Overridepublic TargetEnum process(TargetEnum target) {// Type declaration statementTargetEnum result = target;int value = target.field;
// Synchronized statementsynchronized (this) {value *= 2;}
// Switch statementswitch (target) {case VALUE1:value += 10;break;case VALUE2:value += 20;break;}
// Variable callresult.field = value;result.update(value);
// Overload existsString str1 = result.convert(value);String str2 = result.convert(value, "formatted: ");
// Overloading method in exception throwing (lambda)try {IntConsumer consumer = OtherClass::process;consumer.accept(value);} catch (IllegalArgumentException e) {// No new exception}
return result;}}}}
interface Processable {TargetEnum process(TargetEnum target);}
private enum TargetEnum extends TargetParent {VALUE1, VALUE2;
int field;
TargetEnum() {// Anonymous inner classRunnable init = new Runnable() {@Overridepublic void run() {field = ordinal() * 5;}};init.run();}
void update(int val) {this.field = val;}
// Overloaded methodsString convert(int num) {return String.valueOf(num);}
String convert(int num, String prefix) {return prefix + num;}}
class TargetParent {}
class OtherClass {// Overloading methodspublic static void process(int num) {if (num < 0) {throw new IllegalArgumentException("Negative value");}}
public static void process(int num, String msg) {System.out.println(msg + num);}}