package test;
import java.util.ArrayList;import java.util.List;
abstract class SourceClass {public class MemberInner {@Overridepublic final List<String> process(TargetClass targetParam) {List<String> result = new ArrayList<>();int count = 0;
do {result.add(targetParam.getName());count++;} while (count < 3);
class LocalInner {void addItems() {targetParam.items.forEach(item -> result.add(item));}}new LocalInner().addItems();
return result;}}}
public abstract class TargetClass {protected List<String> items = new ArrayList<>();
public abstract String getName();
public void initialize() {Runnable anon = new Runnable() {@Overridepublic void run() {items.add("init");}};anon.run();}}