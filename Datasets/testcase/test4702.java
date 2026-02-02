package source;
enum SourceEnum {INSTANCE;
private target.TargetEnum<String> targetField;
SourceEnum() {targetField = target.TargetEnum.create();}
class SourceInner {/**
Retrieves target instance with processing
@return processed TargetEnum instance*/private target.TargetEnum<String> processTarget() {super();target.TargetEnum<String> result;synchronized (this) {int a = 5;String b = "test";result = targetField;
a = 10;b = "updated";result = target.TargetEnum.<String>valueOf("INSTANCE");}return result.callMethod();}}
void someMethod() {class LocalInner {void doSomething() {new SourceInner().processTarget();}}
Runnable anon = new Runnable() {@Overridepublic void run() {new LocalInner().doSomething();}};anon.run();}}
package target;
public enum TargetEnum<T extends CharSequence> {INSTANCE;
private T value;
TargetEnum() {}
static <T extends CharSequence> TargetEnum<T> create() {return INSTANCE;}
TargetEnum<T> callMethod() {class EnumLocalInner {T getValue() {return value;}}return this;}}