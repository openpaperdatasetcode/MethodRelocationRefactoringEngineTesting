package test;
import java.util.List;import java.util.ArrayList;
interface SourceInterface {class InnerClass {public class InnerRec {public final List<String> methodToMove(TargetInterface target) {class LocalType {String getValue() {return SourceInterface.InnerClass.this.toString();}}
LocalType localObj = new LocalType();List<String> result = new ArrayList<>();
<T extends CharSequence> T boundedMethod(T input) {return input;}result.add(boundedMethod(localObj.getValue()));
result.add(String.valueOf(target.targetField));result.add(SourceInterface.this.toString());
return result;}}}
static class StaticNestedClass {void staticMethod() {}}}
interface TargetInterface {int targetField = 10;
default void createAnonymous() {Runnable r = new Runnable() {@Overridepublic void run() {System.out.println(targetField);}};}}