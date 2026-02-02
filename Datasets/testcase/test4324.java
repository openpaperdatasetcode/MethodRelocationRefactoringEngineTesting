package same;
import java.util.ArrayList;import java.util.List;
interface Source {Target targetField = new Target() {};static class SourceStaticNested {}
Runnable anonInner = new Runnable() {@Overridepublic void run() {new SourceStaticNested();}};
default List<String> instanceMethod(Target.TargetInner innerParam) {public void sourceAccessorInIf() {this.toString();}
List<String> result = new ArrayList<>();if (innerParam != null) {sourceAccessorInIf();result.add(innerParam.getInnerValue());} else {result.add("null_inner");}
synchronized (this) {result.add("synchronized_block");}
Object var = innerParam;innerParam.toString();
return result;}}
public interface Target {static class TargetStaticNested {}
class TargetInner {private String innerValue = "target_inner_val";
public String getInnerValue() {return innerValue;}}}