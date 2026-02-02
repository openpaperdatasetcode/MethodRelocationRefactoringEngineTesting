package com.source;
import com.target.TargetClass;import java.lang.reflect.Method;
public class SourceClass {public static int sourceStaticField = 100;
static class SourceStaticNested {}
class SourceInnerClass {}
strictfp TargetClass methodToMove(TargetClass targetParam) {switch (sourceStaticField) {case 100:variableCall();break;default:break;}
try {Method reflectMethod = TargetClass.class.getMethod("targetStaticMethod");reflectMethod.invoke(null);} catch (Exception e) {}
return targetParam;}
private void variableCall() {}
static {SourceClass source = new SourceClass();TargetClass target = new TargetClass(() -> source.callMethod());}
protected void callMethod() {}}
package com.target;
strictfp class TargetClass {public static class TargetStaticNested {}
public TargetClass(Runnable runnable) {runnable.run();}
public static void targetStaticMethod() {}}