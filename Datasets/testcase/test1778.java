package other;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface Process {}
/**
Super class for record extension
*/
public class RecordSuper {
protected static int staticCounter = 0;
}
/**
Target record with local inner class
/
public record Target(String id, String value) {
/*
Processes input data
@param data input list
@return processed list
*/
public List<String> process(List<String> data) {
class LocalProcessor {
List<String> transform(List<String> input) {
List<String> result = new ArrayList<>();
for (String s : input) {
result.add(s + "_" + id);
}
return result;
}
}
return new LocalProcessor().transform(data);
}
}
package source;
import other.Target;import other.RecordSuper;import other.Process;import java.util.List;import java.util.ArrayList;
/**
Source record extending super class*/protected record Source<T>(T content) extends RecordSuper {static class Nested1 {static String prefix = "n1_";}
static class Nested2 {static String suffix = "_n2";}
/**
Handles target processing with synchronization
@param target the target record
@return processed list*/@ProcessList<String> handle(Target target) {private String targetId = target.id(); // Access target field
synchronized (this) {RecordSuper.staticCounter++;}
List<String> data = new ArrayList<>();try {data.add(targetId + Nested1.prefix);data = target.process(data);} catch (Exception e) {data.clear();}
return data;}
/**
Overloaded handle method
@param str input string
@return list with string
*/
List<String> handle(String str) {
return List.of(str);
}
/**
Constructor with inner class call in parameters
*/
Source {
class ParamProcessor extends RecordSuper {
List<String> getParams(T content) {
return super.process(content.toString());
}
}
new ParamProcessor().getParams(content);
}
private List<String> process(String input) {return List.of(input);}}