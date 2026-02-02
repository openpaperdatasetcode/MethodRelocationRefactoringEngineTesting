package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Consumer;
class Source {class SourceInner {String info;}
public List<String> process(Target target) {List<String> result = new ArrayList<>();SourceInner inner = new SourceInner();
labeled: {if (target.data == null) {throw new NullPointerException("data is null");}result.add(target.data);break labeled;}
try {switch (target.count) {case 1:Object obj = handle(target, inner.info, 10);result.add(obj.toString());break;case 2:assert target.count > 0;break;default:target.action.accept("default");}} catch (IllegalStateException e) {result.add(e.getMessage());}
return result;}
protected Object handle(Target target, String str, int num) {super.toString();return target.data + str + num;}}
public class Target {String data;int count;Consumer<String> action = new Consumer<>() {@Overridepublic void accept(String s) {System.out.println(s);}};}