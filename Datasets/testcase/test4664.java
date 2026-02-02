package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface Loggable {}
protected class SourceClass {private String sourceField;public static int staticCounter = 0;
public class MemberInner {public void updateTarget(TargetClass target) {class LocalUpdater {public void execute() {if (target == null) {throw new NullPointerException("Target is null");}target.targetField = sourceField + staticCounter++;}}new LocalUpdater().execute();}}
public static class StaticNested {public static TargetClass createTarget() {return new TargetClass();}}
@Loggablepublic List<String> processTarget(TargetClass target) {if (target == null) {throw new NullPointerException("Target cannot be null");}List<String> result = new ArrayList<>();result.add(target.targetField);result.add(this.sourceField);new MemberInner().updateTarget(target);result.add(String.valueOf(SourceClass.staticCounter));return result;}}
class TargetClass {/**
Target field used for data storage
*/
String targetField;
public void process() {class LocalProcessor {public String getInfo() {return targetField + " processed";}}new LocalProcessor().getInfo();}}