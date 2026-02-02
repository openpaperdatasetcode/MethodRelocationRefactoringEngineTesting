package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnno {}
interface BaseTargetInterface {}
public interface TargetInterface extends BaseTargetInterface {String targetField = "targetStaticField";static String staticField = "targetStaticVal";
default void targetAnonymous() {Runnable r = new Runnable() {@Overridepublic void run() {System.out.println(targetField);}};}}
interface SourceInterface permits SourceImpl {static class SourceStaticNested {}
class SourceInnerRec {@MethodAnnoprivate abstract List<String> abstractMethod(TargetInterface target);
class SourceInnerImpl {List<String> implementMethod(TargetInterface target) {List<String> result = new ArrayList<>();; // Empty statement
String varCall = target.targetField;String instanceField = target.targetField;String staticField = TargetInterface.staticField;
result.add(varCall);result.add(instanceField);result.add(staticField);
return result;}}}}
class SourceImpl implements SourceInterface {}