package test;
import java.util.ArrayList;import java.util.List;
interface Processable {String execute(String input);}
class ParentSource {protected ParentSource() {}}
public class SourceClass extends ParentSource {public class InnerSource {@Deprecatedfinal Object instanceMethod(TargetClass target) {super();
class LocalInner {Object handleTarget(TargetClass t) {return t.processData("input");}}
class TypeDecl {String formatResult(Object obj) {return obj.toString().toUpperCase();}}
LocalInner local = new LocalInner();TypeDecl typeDecl = new TypeDecl();
new Runnable() {@Overridepublic void run() {System.out.println(target.getData());}}.run();
try {Object rawResult = local.handleTarget(target);String formatted = typeDecl.formatResult(rawResult);
List<String> overloadedResult = this.overloadedMethod(target, formatted);return overloadedResult;} catch (IllegalArgumentException e) {throw new IllegalArgumentException("Processing failed", e);}}
List<String> overloadedMethod(TargetClass target, String suffix) {List<String> result = new ArrayList<>();result.add(target.getData() + "_" + suffix);return result;}
List<String> overloadedMethod(TargetClass target) {return overloadedMethod(target, "default");}}}
class TargetClass implements Processable {private String data;
public TargetClass(String data) {this.data = data;}
public String getData() {return data;}
@Overridepublic String processData(String input) {class LocalProcessor {String compute(String in) {return in + "_" + data;}}return new LocalProcessor().compute(input);}}
