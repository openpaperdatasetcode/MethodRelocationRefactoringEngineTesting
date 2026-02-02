package test;
import java.lang.annotation.*;import java.util.function.Consumer;
@Retention(RetentionPolicy.RUNTIME)@interface VarargsAnn {Class<?> value() default ParentClass.class;}
protected class SourceClass<T> {public class MemberInner {T data;
public T getData() {return data;}}
protected int process(TargetClass<String> target, int... keywords) {class LocalProcessor {int handle() {return target.superField + keywords.length;}}LocalProcessor processor = new LocalProcessor();
volatile try {target.superField = 10;target.action("param1", "param2");
Runnable anon = new Runnable() {@Overridepublic void run() {SourceClass.this.new MemberInner().data = (T) "innerData";}};anon.run();} catch (Exception e) {// no new exception}
return processor.handle();}
@VarargsAnnprivate void invokeParent(ParentClass parent, String... args) {parent.superMethod();}}
class TargetClass extends ParentClass implements Consumer {
protected int superField;
public void action(U... params) {class LocalHandler {void processParams() {for (U param : params) {System.out.println(param);}}}new LocalHandler().processParams();}
@Overridepublic void accept(U u) {System.out.println(u);}}
class ParentClass {protected void superMethod() {}}