package test.source;
import test.target.TargetClass;import test.target.OtherClass;
sealed class SourceClass implements DataHandler permits SourceSubClass {protected String outerProtectedField = "source_protected";
private Object processTarget(TargetClass targetParam) {class SourceInner {@DeprecatedObject handle() {TargetClass.MemberInner targetInner = targetParam.new MemberInner();Object data = targetInner.process(outerProtectedField);targetParam.setData(data);return data;}}return new SourceInner().handle();}
public Object execute(TargetClass target) {return processTarget(target);}
static class StaticNestedSource {public static final String STATIC_FIELD = "nested_static";}
class MemberInnerSource {void useTarget(TargetClass target) {processTarget(target);}}}
non-sealed class SourceSubClass extends SourceClass {}
interface DataHandler {Object execute(TargetClass target);}
// -----------------------------------------------------------------------------
package test.target;
public class TargetClass implements DataProcessor {private Object data;
@Overridepublic void processData(int type) {switch (type) {case 1:OtherClass.getFirst().getSecond().getThird().staticMethod();break;case 2:OtherClass.getFirst().getSecond().getThird().staticMethod();break;default:break;}}
public Object getData() {return data;}
public void setData(Object data) {this.data = data;}
class MemberInner {public Object process(String input) {return input + "_target_inner";}}}
interface DataProcessor {void processData(int type);}
// -----------------------------------------------------------------------------
package test.target;
public class OtherClass {public static FirstClass getFirst() {return new FirstClass();}
public static class FirstClass {public SecondClass getSecond() {return new SecondClass();}}
public static class SecondClass {public ThirdClass getThird() {return new ThirdClass();}}
public static class ThirdClass {protected static void staticMethod() {}}}
// -----------------------------------------------------------------------------
package test.caller;
import test.source.SourceClass;import test.target.TargetClass;
public class MethodCaller {public static void main(String[] args) {SourceClass source = new SourceSubClass();TargetClass target = new TargetClass();
Object result = source.execute(target);System.out.println(result);
target.processData(1);source.new MemberInnerSource().useTarget(target);}}