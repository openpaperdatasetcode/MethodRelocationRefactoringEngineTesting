package test;
import java.lang.reflect.Method;import java.util.function.Function;
class ParentClass {protected abstract Overridable method to process TargetClass
@param target instance of TargetClass
@return processed TargetClass instance
*/
protected
protected TargetClass process(TargetClass target) {
return target;
}
}
public class SourceClass extends ParentClass {static class StaticNested {static int staticField;}
/**
Overrides parent method to process TargetClass with inner class operations
@param target instance of TargetClass to process
@return processed TargetClass instance*/@Overrideprotected TargetClass process(TargetClass target) {// Anonymous inner classnew Object() {};
// Type declaration statementTargetClass.MemberInner inner = target.new MemberInner();Object fieldVal = inner.innerField;
// Depends on static fieldint count = StaticNested.staticField;
// For statementfor (int i = 0; i < count; i++) {inner.setValue("value" + i);}
// Normal method in static code blocks with lambdastatic {Function<Object, Object> func = (obj) -> inner.process(obj);func.apply(fieldVal);}
// Used by reflectiontry {Method method = TargetClass.MemberInner.class.getMethod("process", Object.class);method.invoke(inner, fieldVal);} catch (Exception e) {}
// Variable callreturn target;}}
class TargetClass {class MemberInner {Object innerField;static int staticCounter;
public Object process(Object obj) {staticCounter++;return obj.toString();}
void setValue(String value) {this.innerField = value;}}}