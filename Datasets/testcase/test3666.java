package test;
interface TargetInterface {String getTargetData();}
sealed class TargetClass implements TargetInterface permits SubTargetClass {public class InnerTarget {private String data;
public InnerTarget(String data) {this.data = data;}
public String getData() {return data;}
public void setData(String data) {this.data = data;}}
@Overridepublic String getTargetData() {return "target_base_data";}}
final class SubTargetClass extends TargetClass {@Overridepublic String getTargetData() {return "sub_target_data";}}
class SourceClass {public static class StaticNested {public static TargetClass.InnerTarget createInnerTarget(String data) {return new TargetClass().new InnerTarget(data);}}
{new Runnable() {@Overridepublic void run() {try {TargetClass target = new TargetClass();TargetClass.InnerTarget inner = target.new InnerTarget("anonymous_init");System.out.println(inner.getData());} catch (Exception e) {e.printStackTrace();}}}.run();}
private abstract TargetClass.InnerTarget abstractMethod(TargetClass target) throws Exception;
public TargetClass.InnerTarget concreteMethod(TargetClass target) {try {TargetClass.InnerTarget inner = StaticNested.createInnerTarget(target.getTargetData());
switch (target.getTargetData().length()) {case 13:inner.setData("base_target_processed");break;case 14:inner.setData("sub_target_processed");break;default:inner.setData("default_processed");}
return abstractMethod(target) != null ? abstractMethod(target) : inner;} catch (Exception e) {return new TargetClass().new InnerTarget("error_default");}}}