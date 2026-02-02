import java.util.List;import java.util.ArrayList;
sealed interface ProcessInterface permits SourceClass {List<String> processData();}
private class SourceClass implements ProcessInterface {private TargetClass targetField = new TargetClass();
@Overridepublic List<String> processData() {List<String> result = new ArrayList<>();String staticVal = TargetClass.StaticFieldHolder.STATIC_FIELD;
switch (staticVal.length()) {case 3:result.add(this.callInnerMethods(targetField));break;case 5:result.add(targetField.getInner().m1().m2().m3());break;default:result.add(staticVal);}
return result;}
private String callInnerMethods(TargetClass target) {try {return target.getInner().m1().m2().m3();} catch (Exception e) {throw new RuntimeException("Inner method call failed", e);}}}
class TargetClass implements DataInterface {static class StaticFieldHolder {static final String STATIC_FIELD = "target";}
private InnerClass inner = new InnerClass();
InnerClass getInner() {return inner;}
private class InnerClass implements NestedInterface {@Overridepublic MiddleClass m1() {return new MiddleClass();}}
class MiddleClass implements MiddleInterface {@Overridepublic FinalClass m2() {Runnable r = new Runnable() {@Overridepublic void run() {}};return new FinalClass();}}
class FinalClass implements FinalInterface {@Overridepublic String m3() {return StaticFieldHolder.STATIC_FIELD;}}}
interface DataInterface {}interface NestedInterface {MiddleClass m1();}interface MiddleInterface {FinalClass m2();}interface FinalInterface {String m3();}