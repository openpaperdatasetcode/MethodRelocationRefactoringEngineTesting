package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Consumer;
public class SourceClass<T> {static class StaticNested {}
{new Runnable() {public void run() {}};}
class Inner {class RecursiveInner {@MyAnnotation(action = OthersClass::process)public List<String> getValues(TargetClass target) {super();boolean flag = true;TargetClass.StaticNested.staticField = 1;;
labeled: {target.setValue("test");break labeled;}
List<String> list = new ArrayList<>() {{add(target.process(s -> s.toUpperCase()));}};
try {Consumer<List<String>> consumer = publicGenericMethod();consumer.accept(list);} catch (Exception e) {}
return list;}}}
public Consumer publicGenericMethod() {
int val = 1;
OtherGeneric other = new OtherGeneric<>();
return other::accept;
}
}
public class TargetClass {static class StaticNested {static int staticField;}
private String value;
public void setValue(String value) {this.value = value;}
public String process(Converter<String, String> converter) {return converter.convert(value);}}
@FunctionalInterfaceinterface Converter<F, T> {T convert(F from);}
class OthersClass {static <T> void process(T t) {}}
class OtherGeneric<T> {void accept(T t) {}}
@interface MyAnnotation {Class<?> action();}