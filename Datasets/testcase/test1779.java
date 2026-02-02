package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Consumer;import java.util.function.Function;
private class Target {class MemberInner {private String data;
String getData() {return data;}
void setData(String data) {this.data = data;}}}
protected class Source {public <T> List<String> convert(T input) {List<String> result = new ArrayList<>();result.add(input.toString());return result;}
Object process(Target target) {Target.MemberInner inner = target.new MemberInner();inner.setData("test_data");
// Lambda expressions (3 instances)Function<String, Integer> lengthFunc = s -> s.length();Consumer<String> printConsumer = s -> System.out.println(s);Runnable resetRunnable = () -> inner.setData("");
// Anonymous inner classes (2 instances)Runnable anonTask1 = new Runnable() {@Overridepublic void run() {inner.setData("anon1");}};
Runnable anonTask2 = new Runnable() {@Overridepublic void run() {inner.setData("anon2");}};
List<String> items = this.convert(inner.getData());for (String item : items) {printConsumer.accept(item);int len = lengthFunc.apply(item);inner.setData(item + "_" + len);}
resetRunnable.run();anonTask1.run();anonTask2.run();
return inner.getData();}}