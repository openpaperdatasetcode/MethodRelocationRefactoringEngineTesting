import java.io.IOException;import java.lang.reflect.Method;
enum SourceEnum {INSTANCE;
static class StaticNested {}
{Runnable r = new Runnable() {public void run() {INSTANCE.process(TargetEnum.VALUE);}};}
protected void process(TargetEnum target) {try {if (target == null) {return;}
target.callNestedMethod();TargetEnum.StaticNested.staticAction();
Method method = TargetEnum.StaticNested.class.getMethod("staticAction");method.invoke(null);} catch (IOException e) {// Handle IOException without new exception} catch (Exception e) {// Handle reflection exceptions}}}
/**
Javadoc for TargetEnum
Enum with static nested class and instance method*/public enum TargetEnum {VALUE;
static class StaticNested {public static void staticAction() {}}
void callNestedMethod() throws IOException {StaticNested.staticAction();}}