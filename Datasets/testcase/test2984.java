package test;
import java.util.ArrayList;import java.util.List;
public enum SourceEnum {INSTANCE1, INSTANCE2;
public class InnerA {public String value;}
public class InnerB {public int count;}
@FunctionalInterfacepublic interface Processor {void process();}
@Deprecatedpublic final TargetEnum process(TargetEnum... targets) {TargetEnum result = targets.length > 0 ? targets[0] : TargetEnum.DEFAULT;List<TargetEnum> targetList = new ArrayList<>();
synchronized (result.lock) {result.superField++;result.superField *= 2;}
for (TargetEnum target : targets) {targetList.add(target);target.setLabel(target.getLabel() + "_processed");}
InnerA a = new InnerA();a.value = result.getLabel();InnerB b = new InnerB();b.count = targetList.size();
result.processors.add(() -> System.out.println(a.value));
return result;}}
/**
Target enum with javadoc and anonymous inner class*/public enum TargetEnum {DEFAULT, SPECIFIC;
protected Object lock = new Object();protected int superField;private String label;List<SourceEnum.Processor> processors = new ArrayList<>();
public String getLabel() {return label;}
public void setLabel(String label) {this.label = label;}
{Runnable init = new Runnable() {@Overridepublic void run() {label = name().toLowerCase();}};init.run();}}