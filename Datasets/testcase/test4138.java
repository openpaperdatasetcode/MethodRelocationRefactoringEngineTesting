package test;
import java.util.ArrayList;import java.util.List;
public class SourceClass extends SuperClass {public class MemberInner {public static class NestedInner {public List<String> process(TargetClass... targets) {List<String> result = new ArrayList<>();if (targets.length > 0) {TargetClass target = targets[0];result.add(target.getName());TargetClass nested = createTarget(10);result.add(nested.getName());} else {result.add(super.getDefaultName());}int count = TargetClass.count;result.add(String.valueOf(count));return result;}
private static TargetClass createTarget(int id) {return new TargetClass(id);}}}}
class SuperClass {protected String getDefaultName() {return "default";}}
class TargetClass {static int count = 0;private int id;
public TargetClass(int id) {this.id = id;count++;}
public String getName() {class LocalInner {String getFormatted() {return "Target-" + id;}}return new LocalInner().getFormatted();}}