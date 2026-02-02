package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
interface SourceInterface {// Static method to testpublic static List<String> staticMethod(TargetInterface target) {List<String> result = new ArrayList<>();
// Local inner classclass LocalProcessor<T extends CharSequence> {void process(T value) {result.add(value.toString());}}new LocalProcessor<String>().process("local_data");
// Anonymous inner classRunnable runner = new Runnable() {@Overridepublic void run() {result.add("anonymous_executed");}};runner.run();
// Enhanced for statementString[] data = {"a", "b", "c"};for (String s : data) {result.add(s);}
// OuterClass.this.x (interface this reference)result.add(SourceInterface.this.getClass().getSimpleName());
// Used by reflectiontry {Method method = TargetInterface.class.getMethod("getName");result.add(method.invoke(target).toString());} catch (Exception e) {// No new exception}
// Variable callresult.add(target.getName());
// With boundsclass BoundedHandler<T extends Number & Comparable<T>> {void handle(T num) {result.add(num.toString());}}new BoundedHandler<Integer>().handle(100);
// Instance code blocks with 2 varargs methods (target, super call)class InstanceBlockHolder implements TargetInterface {{TargetInterface t1 = combine("one", "two");TargetInterface t2 = combine("three", "four", "five");result.add(t1.getName());result.add(t2.getName());}
@Overridepublic String getName() {return "holder";}
@Overridepublic default TargetInterface combine(String... parts) {return TargetInterface.super.combine(parts);}}new InstanceBlockHolder();
return result;}}
strictfp interface TargetInterface {String getName();
default TargetInterface combine(String... parts) {return new TargetInterface() {@Overridepublic String getName() {return String.join(",", parts);}};}}