package test;
import java.util.ArrayList;import java.util.List;
class SourceClass {class SourceInner {private List<String> process(TargetClass target) {super();List<String> result = new ArrayList<>();
if (target.count < 0) {throw new IllegalArgumentException("Count negative: " + target.count);}if (target.name == null) {throw new NullPointerException("Name null: " + target.name);}
Object obj = target;TargetClass casted = (TargetClass) obj;result.add(casted.name);
result.add(target.getValue());result.add(target.getValue(10));
return result;}}}
public class TargetClass {int count;String name;
class TargetInner {public String getDetail() {return name + ":" + count;}}
public static TargetClass create() {return new TargetClass();}
public String getValue() {return name;}
public String getValue(int suffix) {return name + suffix;}
public void useCalls() {TargetClass obj = TargetClass.create();String res = (obj != null) ? obj.new TargetInner().getDetail() : "default";}}
