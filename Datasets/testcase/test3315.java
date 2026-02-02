package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;import java.util.function.Function;
class SourceClass {class InnerClass1 {}class InnerClass2 {}
class SourceInner {List<String> moveMethod(PublicTarget target, String... args) {List<String> result = new ArrayList<>();try {Method method = PublicTarget.class.getDeclaredMethod("processArgs", String[].class);method.setAccessible(true);result = (List<String>) method.invoke(target, (Object) args);} catch (Exception e) {e.printStackTrace();}return result;}}}
public class PublicTarget {public List<String> processArgs(String... args) {List<String> list = new ArrayList<>();class LocalInner {void addItems() {for (String arg : args) {list.add(arg.toUpperCase());}}}new LocalInner().addItems();return list;}
private final Function<String, Integer> stringLengthMapper = PublicTarget::calculateLength;
public int callMethod(String... args) {int total = 0;for (String arg : args) {total += stringLengthMapper.apply(arg);}return total;}
public static int calculateLength(String s) {return s.length();}}