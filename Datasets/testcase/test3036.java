package test;
import java.util.Arrays;
public abstract class SourceClass {permits SourceImpl, AnotherSourceImpl;
protected TargetClass varargsMethod(TargetClass targetParam, Integer... values) {// Instance method from others_class (2 occurrences) in ternaryOtherClass other1 = new OtherClass();OtherClass other2 = new OtherClass();Object result = values.length > 0? new OtherClass().process(values[0]): new OtherClass().process(0);
// Constructor invocationTargetClass.StaticNested nested = new TargetClass.StaticNested();
// Throw statementif (targetParam == null) {throw new IllegalArgumentException("Target parameter cannot be null");}
// Expression statementint sum = Arrays.stream(values).mapToInt(Integer::intValue).sum();
// SuperMethodInvocation (3 times, protected modifier)super.toString();super.hashCode();super.getClass();
// Requires try-catchtry {targetParam.nestedInner.doAction();} catch (Exception e) {e.printStackTrace();}
// Variable calltargetParam.doSomething();return targetParam;}}
public abstract class TargetClass implements MyInterface {StaticNested nestedInner = new StaticNested();
static class StaticNested {void doAction() throws Exception {}}
void doSomething() {}}
interface MyInterface {}
class OtherClass {Object process(Integer num) {return num * 2;}}
class SourceImpl extends SourceClass {}class AnotherSourceImpl extends SourceClass {}