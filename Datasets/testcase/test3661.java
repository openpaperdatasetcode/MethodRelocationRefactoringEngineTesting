package test;
import java.util.ArrayList;import java.util.List;
public class SourceClass {public static class StaticNested {public String getStaticData() {return "static_nested_data";}}
{new Runnable() {@Overridepublic void run() {new SourceClass().privateInstanceMethod(new TargetClass());}}.run();}
private List<String> privateInstanceMethod(TargetClass target) {List<String> result = new ArrayList<>();
if (target == null) {throw new IllegalArgumentException("TargetClass parameter cannot be null");}
TargetClass.InnerTarget inner1 = target.new InnerTarget("first");TargetClass.InnerTarget inner2 = new TargetClass.InnerTarget(inner1) {@Overridepublic String getData() {return super.getData() + "_anonymous";}};
result.add(inner1.getData());result.add(inner2.getData());result.add(target.processInner(inner1));result.add(StaticNested.getStaticData());
return result;}}
class TargetClass {public class InnerTarget {private String data;
public InnerTarget(String data) {this.data = data;}
public InnerTarget(TargetClass.InnerTarget other) {super();this.data = other.data + "_copied";}
public String getData() {return data;}}
public String processInner(InnerTarget inner) {return "processed_" + inner.getData();}}