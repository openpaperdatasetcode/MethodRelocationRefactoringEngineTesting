package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Function;
abstract class SourceClass {private String outerPrivate = "private_data";
// Static nested classpublic static class SourceStaticNested {// Inner class containing the static methodpublic class NestedInner {protected static void staticMethod(TargetClass target) {// Local inner classclass LocalProcessor {void handle() {// Variable callString targetData = target.data;
// Access outer privateString combined = targetData + "_" + outerPrivate;
// Requires try-catchtry {int length = combined.length();target.setData(combined + "_len:" + length);} catch (NullPointerException e) {target.setData("error");}}}new LocalProcessor().handle();}}}
// Functional interface usage with call method (lambda)public void useFunctionalInterface(TargetClass target) {Function<TargetClass, List<String>> processor = (t) -> processTarget(t);List<String> result = processor.apply(target);System.out.println(result);}
// Protected instance method for lambdaprotected List<String> processTarget(TargetClass target) {List<String> list = new ArrayList<>();list.add(target.data);list.add(target.new Inner().getDetails());return list;}}
strictfp abstract class TargetClass {String data;
// Member inner classpublic class Inner {public String getDetails() {return "inner_" + data;}}
public void setData(String data) {this.data = data;}}