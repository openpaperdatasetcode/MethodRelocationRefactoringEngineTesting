package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
sealed enum SourceEnum permits A, B {A, B;
// Static nested classpublic static class SourceStaticNested {}
// Member inner classpublic class SourceMemberInner {private String data;
public String getData() {return data;}
public void setData(String data) {this.data = data;}}
// Static code block with accessor methodstatic {SourceEnum source = A;SourceMemberInner inner = source.new SourceMemberInner();inner.setData("static_init");Object value = inner.getData();System.out.println("Static block value: " + value);}
private Object instanceMethod(TargetEnum.InnerRec targetRec) {// Variable callObject result = targetRec.value();
// Access instance methodresult = result + "_" + targetRec.process();
// This variable assignmentSourceMemberInner inner = this.new SourceMemberInner();inner.setData(targetRec.value().toString());this.innerVar = inner;
// With boundsclass BoundedProcessor<T extends CharSequence & Comparable<T>> {T process(T input) {return (T) (input + "processed");
}
}
BoundedProcessor<String> processor = new BoundedProcessor<>();
result = result + "" + processor.process(targetRec.value().toString());
// Assert statementassert targetRec.id() >= 0 : "ID must be non-negative";
// Used by reflectiontry {Method method = TargetEnum.InnerRec.class.getMethod("value");result = result + "_reflection:" + method.invoke(targetRec);} catch (Exception e) {// No new exception}
return result;}
// Instance variable for this.var assignmentprivate SourceMemberInner innerVar;}
public enum TargetEnum {X, Y;
{// Anonymous inner classRunnable initializer = new Runnable() {@Overridepublic void run() {System.out.println("TargetEnum initialized: " + name());}};initializer.run();}
public record InnerRec(int id, Object value) {public String process() {return value.toString().toUpperCase();}}}