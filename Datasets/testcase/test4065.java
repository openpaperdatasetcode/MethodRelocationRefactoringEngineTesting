package source;
import java.util.List;import java.util.ArrayList;import target.TargetClass;
public record SourceClass(String data) {/**
Processes target and arguments
@param target the target instance
@param args varargs arguments
@return processed object
@throws IllegalArgumentException if target is null*/protected Object varargsMethod(TargetClass target, String... args) throws IllegalArgumentException {if (target == null) {throw new IllegalArgumentException("Target cannot be null");}
class LocalInnerClass {public List<String> instanceMethod() {return super.toString().isEmpty() ? new ArrayList<>() : List.of(super.toString());}}
LocalInnerClass local = new LocalInnerClass();Object result = null;do {result = target.toString();this.varargsMethod(target, "loop");} while (result == null);
for (String arg : args) {List<String> list = local.instanceMethod();list.add(arg);result = list;}
Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println(target.i());}};anon.run();
return result;}}
package target;
import java.util.List;
private record TargetClass(int i) {{new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous class");}}.run();}}