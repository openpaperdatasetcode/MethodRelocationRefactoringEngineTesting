package test;
import java.util.ArrayList;import java.util.List;import java.util.stream.Collectors;
class SourceClass {// Static nested classpublic static class SourceStaticNested {}
// Member inner classprotected class SourceInner {@MyAnnotationprotected TargetClass varargsMethod(TargetClass... targets) {if (targets.length == 0) {return new TargetClass();}TargetClass result = targets[0];
// Collection operations with static method (inner_class)List<String> processed = new ArrayList<>();for (TargetClass target : targets) {processed.addAll(TargetClass.InnerClass.processStatic(target.field));}result.field = processed.stream().collect(Collectors.joining(","));
// Variable callresult.inner = new TargetClass.InnerClass();result.inner.setValue(result.field.length());
// Do-while with call method (overloading, lambda)int i = 0;do {String str = result.inner.process(s -> s + "_" + i, i);result.field += str;i++;} while (i < 2);
return result;}}
// Local inner classpublic void useLocal() {class LocalHandler {void execute() {TargetClass target = new TargetClass();new SourceInner().varargsMethod(target);}}new LocalHandler().execute();}}
@interface MyAnnotation {}
private class TargetClass {String field;InnerClass inner;
// Member inner classpublic class InnerClass {private int value;
public void setValue(int value) {this.value = value;}
// Overloaded methods with lambdaString process(Converter<String, String> converter, int num) {return converter.convert(field) + num;}
String process(Converter<String, String> converter) {return converter.convert(field);}
// Static method for collection operationsprotected static List<String> processStatic(String s) {List<String> list = new ArrayList<>();list.add(s.toUpperCase());return list;}}}
interface Converter<T, R> {R convert(T t);}