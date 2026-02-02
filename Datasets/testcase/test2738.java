package test.same;
import java.util.ArrayList;import java.util.List;
public class SourceClass<T> {class MemberInner {private List<String> getValue(TargetClass<String> target) {List<String> list = new ArrayList<>();int num = 2;
for (int i = 0; i < num; i++) {list.add(String.valueOf(target.superField));if (i == 1) break;}
synchronized (this) {OthersClass.overrideMethod(3);}
target.prop = new Runnable() {public void run() {}};
return list;}}
Runnable anon = new Runnable() {public void run() {}};}
/**
Javadoc for TargetClass*/final class TargetClass<T> extends SuperClass {Runnable prop;
TargetClass() {Runnable anon = new Runnable() {public void run() {}};}}
class SuperClass {protected int superField;}
class OthersClass {static void overrideMethod(int val) {}}