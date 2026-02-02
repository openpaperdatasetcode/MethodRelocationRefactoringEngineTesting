package com.source;
import com.target.TargetClass;import java.lang.reflect.Method;
class SourceClass {static class StaticNested {}
{Runnable r = new Runnable() {public void run() {new SourceClass().process(new TargetClass());}};}
public int process(TargetClass target) {super();int count = 0;
while (count < 3) {try {// Call target inner class method via reflection (object initialization pos)Method innerMethod = TargetClass.class.getDeclaredMethod("getInnerValue", String.class);innerMethod.setAccessible(true);String result = (String) innerMethod.invoke(target, "param_" + count);
// Variable calltarget.updateCount(count);count++;} catch (Exception e) {count++;}}
return count;}}
// Different package: com.targetpackage com.target;
interface DataProcessor {String process(String input);}
private class TargetClass implements DataProcessor {private int count;
@Overridepublic String process(String input) {return input.toUpperCase();}
private String getInnerValue(String arg) {class LocalInner {String handle(String input) {return process(input) + "_inner";}}return new LocalInner().handle(arg);}
void updateCount(int val) {this.count = val;}}