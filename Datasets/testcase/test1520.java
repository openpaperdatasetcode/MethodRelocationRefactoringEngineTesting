package test;
import java.util.ArrayList;import java.util.List;
/**
Target class with Javadoc
Provides data storage and processing capabilities*/public class Target {private String data;private int id;
/**
Constructs a Target instance
@param data the string data
@param id the identifier
*/
public Target(String data, int id) {
this.data = data;
this.id = id;
}
/**
Gets the data value
@return the stored data
*/
public String getData() {
return data;
}
/**
Gets the identifier
@return the id
*/
public int getId() {
return id;
}
}
protected class Source {static class FirstStaticNested {/**
Overloaded static method
@param target the Target instance
@return the processed Target
*/
public static Target process(Target target) {
return new Target(target.getData() + "_processed", target.getId() + 1);
}
}
static class SecondStaticNested {class Inner {/**
Method in source_inner position
@param target the Target parameter
@return processed Object*/protected Object handle(Target target) {// Object initialization with overloading method call (1)Target processedTarget = FirstStaticNested.process(target);
// Overload exists (demonstrated by possible another overload)List<String> overloadResult = processOverload(target.getData());
// Variable callString targetData = target.getData();int targetId = target.getId();
// Assert statementassert processedTarget.getId() > targetId : "ID not incremented";
// Requires try-catchtry {if (processedTarget.getData() == null) {throw new NullPointerException("Processed data is null");}} catch (NullPointerException e) {// Handle exception without throwing new onee.printStackTrace();}
return List.of(processedTarget, overloadResult);}
/**
Overloaded instance method
@param input the string input
@return list of processed strings
*/
private List<String> processOverload(String input) {
List<String> result = new ArrayList<>();
result.add(input.toUpperCase());
return result;
}
}
}
}