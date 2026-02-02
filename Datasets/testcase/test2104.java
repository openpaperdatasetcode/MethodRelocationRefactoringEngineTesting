package test;
import java.util.List;import java.util.ArrayList;
public class SourceClass {class FirstInner {class InnerRecursive {List<String> methodToMove(TargetClass targetParam) {super();List<String> result = new ArrayList<>();
targetParam.new InnerClass().new InnerRecursive().firstMethod();int val = targetParam.new InnerClass().new InnerRecursive().secondMethod(5);result.add(String.valueOf(val));
targetParam.variableCall();targetParam.new InnerClass().localInnerMethod();
Runnable r = new Runnable() {public void run() {}};
return result;}}}
void createLocalInner() {class LocalInner {}}}
private class TargetClass {class InnerClass {class InnerRecursive {void firstMethod() {}int secondMethod(int num) { return num * 2; }}
void localInnerMethod() {class LocalInner {}}}
void variableCall() {}}