import java.util.Objects;
abstract class SourceAbstract {public static class StaticNested {public static <T extends TargetAbstract> T getTargetInstance(Class<T> clazz) throws InstantiationException, IllegalAccessException {return clazz.newInstance();}}
final TargetAbstract varargsMethod(TargetAbstract... targets) {if (targets == null || targets.length == 0) {return new TargetConcrete();}
TargetAbstract selectedTarget = targets[0];for (TargetAbstract target : targets) {variableCall(target);selectedTarget = (target.getLocalData().length() > selectedTarget.getLocalData().length())? target: selectedTarget;}
new Runnable() {@Overridepublic void run() {System.out.println("Selected target data: " + selectedTarget.getLocalData());}}.run();
return selectedTarget;}
private void variableCall(TargetAbstract target) {target.setLocalData(target.getLocalData() + "_updated");}
protected Object accessorMethod(TargetAbstract target) {return target.getLocalData();}}
abstract class TargetAbstract {private String localData = "default_data";
public String getLocalData() {class LocalInner {String fetchData() {return localData;}}return new LocalInner().fetchData();}
public void setLocalData(String data) {this.localData = data;}}
class TargetConcrete extends TargetAbstract {@Overridepublic String getLocalData() {return super.getLocalData() + "_concrete";}}