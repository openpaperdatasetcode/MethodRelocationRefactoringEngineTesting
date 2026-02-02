package test;
import java.util.List;import java.util.ArrayList;
private class TargetClass {public String targetField;public void targetMethod() {class LocalInner {}}}
class SourceClass {class Inner1 {}class SourceInner {protected List<String> methodToMove(TargetClass target) {List<String> result = new ArrayList<>();result.add(target.targetField);
SourceClass outer = new SourceClass();List<String> varargsResult = outer.new Inner2().synchronizedVarargsMethod(target, "arg1", "arg2", "arg3");
return varargsResult;}}
class Inner2 {private synchronized List<String> synchronizedVarargsMethod(TargetClass target, String... args) {List<String> data = new ArrayList<>();data.add(target.targetField);for (String arg : args) {data.add(arg);}return data;}}
public void callFunctionalInterface() {TargetClass target = new TargetClass();SourceInner inner = new SourceInner();
Runnable r = inner::methodToMove;OtherClass other = new OtherClass();Runnable r2 = other::abstractMethod;}}
class OtherClass {private abstract void abstractMethod() {super.toString();}}